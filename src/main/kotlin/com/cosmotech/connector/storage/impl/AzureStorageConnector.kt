package com.cosmotech.connector.storage.impl

import com.azure.storage.blob.BlobClient
import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobServiceClient
import com.azure.storage.blob.BlobServiceClientBuilder
import com.cosmotech.connector.commons.Connector
import com.cosmotech.connector.commons.pojo.CsvData
import com.cosmotech.connector.storage.utils.AzureStorageUtil
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.Logger

/**
 * Azure Storage connector.
 * @param dataToStore the CsvData's list containing all data to store into Azure Storage
 */
class AzureStorageConnector(
    private val dataToStore:List<CsvData>):
        Connector<BlobServiceClient,
            Map<String,CsvData>,
            Map<String,CsvData>>{

    companion object {
        val LOGGER: Logger = Logger.getLogger(AzureStorageConnector::class.simpleName)
    }

    override fun createClient(): BlobServiceClient {
        val connectStr = AzureStorageUtil.getConnectionString()
        LOGGER.info("Client creation")
        return BlobServiceClientBuilder()
            .connectionString(connectStr)
            .buildClient()
    }

    override fun prepare(client: BlobServiceClient): Map<String,CsvData> {
        val blobContainer = getOrCreateBlobContainer(client)
        var blobClient: BlobClient
        val result = mutableMapOf<String,CsvData>()
        val fileTree = AzureStorageUtil.generateFileTree()
        dataToStore.forEach {
            // Verify that the files have been created (else create them)
            val filePathName = it.getExportFilePath()
            if (!Files.exists(Paths.get(filePathName)))
                it.writeFile()
            val fileName =
                AzureStorageUtil.constructHierarchicalFileName(fileTree, it)
            blobClient = blobContainer.getBlobClient(fileName)
            LOGGER.info("Uploading to Blob storage as blob:${blobClient.blobUrl}")
            blobClient.uploadFromFile(filePathName,true)
            result[blobClient.blobUrl] = it
        }
        return result
    }

    override fun process(): Map<String,CsvData>{
        val blobServiceClient = createClient()
        return prepare(blobServiceClient)
    }

    /**
     * Get or create the blob container that will be used to store data into Azure Storage
     * @param client the Azure Storage client
     * @return a BlobContainerClient object
     */
    private fun getOrCreateBlobContainer(client: BlobServiceClient): BlobContainerClient {
        val containerName = AzureStorageUtil.getBlobContainerName()
        val blobContainer = client.getBlobContainerClient(containerName)
        if (!blobContainer.exists())
            client.createBlobContainer(containerName)
        return blobContainer
    }

}