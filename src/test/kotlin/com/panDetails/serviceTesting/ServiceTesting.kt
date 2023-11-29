package com.panDetails.serviceTesting

import com.panDetails.utils.H2Database
import com.panDetails.plugins.PanNumberNotExistException
import com.panDetails.services.PanServices
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ServiceTesting {
    private val panServices = PanServices()
    @Before
    fun setUp() {
         H2Database.init()
    }
    @After
    fun drop() {
        H2Database.dropTestDb()
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

