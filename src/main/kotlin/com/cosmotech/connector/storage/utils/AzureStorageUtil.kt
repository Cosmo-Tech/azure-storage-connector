package com.cosmotech.connector.storage.utils

import com.cosmotech.connector.commons.pojo.CsvData
import com.cosmotech.connector.storage.constants.AZURE_STORAGE_BLOB_CONTAINER_NAME
import com.cosmotech.connector.storage.constants.AZURE_STORAGE_CONNECTION_STRING_KEY
import org.eclipse.microprofile.config.Config
import org.eclipse.microprofile.config.ConfigProvider
import java.util.*

/**
 * Utility class for Azure Storage Connector
 */
class AzureStorageUtil {

    companion object Builder {

        private val configuration: Config = ConfigProvider.getConfig()

        /**
         * Get the connection string for the client
         * @return the connection string
         */
        @JvmStatic
        fun getConnectionString(): String =
            configuration.getValue(AZURE_STORAGE_CONNECTION_STRING_KEY,String::class.java)

        /**
         * Get the blob container name
         * @return the blob container name
         */
        @JvmStatic
        fun getBlobContainerName(): String =
            configuration.getValue(AZURE_STORAGE_BLOB_CONTAINER_NAME,String::class.java)

        /**
         * Build a hierarchical file name
         * @param fileTree the directory path
         * @param it the CsvData containing file's info
         * @return the hierarchical file name
         */
        @JvmStatic
        fun constructHierarchicalFileName(
            fileTree: String,
            it: CsvData
        ):String = "$fileTree${it.fileName}${it.extension}"

        //TODO adapt method in order to have relevant data hierarchy
        /**
         * Generate a hierarchical path regarding the passing parameters
         * @return the directory's path
         */
        @JvmStatic
        fun generateFileTree(): String {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)
            return "$year/$month/$day/$hour/$minute/$second/"
        }

    }


}