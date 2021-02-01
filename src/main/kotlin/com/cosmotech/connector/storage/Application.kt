package com.cosmotech.connector.storage

import com.cosmotech.connector.commons.pojo.CsvData
import com.cosmotech.connector.storage.impl.AzureStorageConnector
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Main function.
 * Here, this is just an example of how to use the Azure storage connector
 */
fun main() {

/*      Minimal how-to

        // Working test directory
        val testTempFolder = "/tmp/AzureStorageConnector"
        Files.createDirectories(Paths.get(testTempFolder))

        // Construct simple data
        val csvData = mutableListOf(
            CsvData(
                "Test1",
                mutableMapOf("column1" to "string", "column2" to "datetime", "column3" to "int"),
                mutableListOf(
                    mutableListOf("data11", "2020-09-01T00:00:00+00:00", "1"),
                    mutableListOf("data21", "2020-09-02T00:00:00+00:00", "2"),
                    mutableListOf("data31", "2020-09-03T00:00:00+00:00", "3")
                ),
                testTempFolder,
            ),
            CsvData(
                "Test2",
                mutableMapOf("column4" to "string", "column5" to "datetime", "column6" to "int"),
                mutableListOf(
                    mutableListOf("data41", "2020-09-05T00:00:00+00:00", "4"),
                    mutableListOf("data51", "2020-09-06T00:00:00+00:00", "5"),
                    mutableListOf("data61", "2020-09-07T00:00:00+00:00", "6")
                ),
                testTempFolder,
            )
        )

        // Construct the connector and call it
        AzureStorageConnector(csvData).process()*/

    }


