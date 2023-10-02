package com.example.testing

import com.example.H2Databse
import com.example.database.ProductsEntity
import com.example.repositories.ProductRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import java.sql.Connection
import kotlin.test.*

class RepositoryTesting
{
    private lateinit var database:Database
    private val productRepo = ProductRepository()
    @Before
    fun setUp()  {
        database = H2Databse.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(ProductsEntity)
        }
    }
    @After
    fun drop() {
        transaction(database) {
            SchemaUtils.drop(ProductsEntity)
        }
    }
    @Test
     fun testRepoGetAllProducts()= runBlocking {
        productRepo.getAllProducts().apply {
          assertTrue(this.toString().isNotEmpty())
        }
    }
    @Test
    fun testRepoSearchProducts(): Unit = runBlocking {
        productRepo.getAllProducts()
        productRepo.searchProducts("MacBook Pro").apply {
            assertEquals(6, this[0].id)
        }
        productRepo.searchProducts(("Mac")).apply {
            assertEquals(emptyList(),this)
        }
    }
    @Test
    fun testRepoFetchProducts(): Unit = runBlocking {
        productRepo.getAllProducts()
        productRepo.fetchProductsByCategory("smartphones").apply {
            assertEquals(3,this[2].id)
        }
        productRepo.fetchProductsByCategory("mobiles").apply {
            assertEquals(emptyList(),this)
        }
    }
    @Test
    fun testRepoDeleteProduct():Unit= runBlocking {
        productRepo.getAllProducts()
        productRepo.deleteProductsByCategory("smartphones").apply {
            assertEquals(5,this)
        }
        productRepo.deleteProductsByCategory("mobiles").apply {
            assertEquals(0,this)
        }
    }
    @Test
    fun testRepoUpdateProduct():Unit= runBlocking {
        productRepo.getAllProducts()
        productRepo.updateProduct(1,"iPhone 14").apply {
            assertEquals(1,this)
        }
        productRepo.updateProduct(232,"null").apply {
            assertEquals(0,this)
        }
    }
}