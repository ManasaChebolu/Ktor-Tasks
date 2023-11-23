package com.panDetails.serviceTesting

import com.panDetails.H2Database
import com.panDetails.databaseFactory.tables.OcrInfoTable
import com.panDetails.plugins.PanNumberNotExistException
import com.panDetails.services.PanServices
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ServiceTesting {
    private lateinit var database: Database
    private val panServices = PanServices()

    @Before
    fun setUp() {
        database= H2Database.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(OcrInfoTable)
        }
    }
    @After
    fun drop() {
        transaction(database) {
            SchemaUtils.drop(OcrInfoTable)
        }
    }

    @Test
    fun testServicePanDetails() :Unit = runBlocking {
        panServices.panRepoResult("DMIPB1989Q").apply {
            assertEquals("success",this.infoMsg)
        }
        assertFailsWith<PanNumberNotExistException> {
            panServices.panRepoResult("DMIPB198")
        }
        assertFailsWith<PanNumberNotExistException> {
            panServices.panRepoResult("null")
        }
    }
}

