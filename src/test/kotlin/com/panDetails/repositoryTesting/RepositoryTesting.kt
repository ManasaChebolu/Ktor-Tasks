package com.panDetails.repositoryTesting

import com.panDetails.utils.H2Database
import com.panDetails.plugins.PanNumberNotExistException
import com.panDetails.repositories.PanRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RepositoryTesting {
    private val panRepo = PanRepository()

    @Before
    fun setUp() {
        H2Database.init()
    }
    @After
    fun drop() {
        H2Database.dropTestDb()
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

