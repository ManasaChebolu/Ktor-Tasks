package com.panDetails.repositoryTesting

import com.panDetails.H2Database
import com.panDetails.databaseFactory.tables.OcrInfoTable
import com.panDetails.plugins.PanNumberNotExistException
import com.panDetails.repositories.PanRepository
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

class RepositoryTesting {
    private lateinit var database: Database
    private val panRepo = PanRepository()

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
    fun testRepoPanDetails():Unit = runBlocking {
        panRepo.postPanDetailsRepo("DMIPB1989Q").apply {
            assertEquals("BUVANESWARI MEGANATHAN",this.name)
        }
        assertFailsWith<PanNumberNotExistException> {
            panRepo.postPanDetailsRepo("DMIPB198")
        }
    }
}

