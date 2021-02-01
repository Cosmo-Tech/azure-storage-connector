package com.cosmotech.connector.storage.utils

import com.cosmotech.connector.commons.pojo.CsvData
import com.cosmotech.connector.storage.AbstractUnitTest
import org.junit.Test
import kotlin.test.assertEquals

class AzureStorageUtilTest: AbstractUnitTest() {

    private val defaultCsvData = CsvData(
        "Test1",
        mutableMapOf(
            "column1" to "string",
            "column2" to "datetime",
            "column3" to "int"
        ),
        mutableListOf(
            mutableListOf("data11", "data12", "1"),
            mutableListOf("data21", "data22", "2"),
            mutableListOf("data31", "data32", "3")
        ),
        "/tmp/",
    )

    @Test
    fun test_constructHierarchicalFileName() {
        val fileTree = "/directory/test/path/"
        val hierarchicalFileName =
            AzureStorageUtil.constructHierarchicalFileName(fileTree, defaultCsvData)
        assertEquals(fileTree.plus(defaultCsvData.fileName).plus(defaultCsvData.extension),hierarchicalFileName)
    }

    //TODO test generateFileTree method


}