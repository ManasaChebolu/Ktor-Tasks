package com.example.testing

import com.example.H2Databse
import com.example.database.ProductsEntity
import com.example.models.ResponseMessage
import com.example.repositories.ProductRepository
import com.example.services.ProductServices
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import java.sql.Connection
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ServiceTesting {
    private lateinit var database: Database
    private val productService=ProductServices()
    private val productRepo = ProductRepository()

    @Before
    fun setUp() {
       database= H2Databse.init()
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
    fun testServiceGetAllProducts(): Unit = runBlocking {
        productService.handleGetAllProducts().apply {
            assertTrue(this.toString().isNotEmpty())
        }
        assertFailsWith<InternalError> {
            productService.handleGetAllProducts()
        }
    }
    @Test
    fun testServiceSearchProducts(): Unit = runBlocking {
        productRepo.getAllProducts()
        productService.handleSearchProducts("MacBook Pro").apply {
            assertEquals(6,this[0].id)
        }
        productService.handleSearchProducts("Mac").apply {
            assertEquals(emptyList(),this)
        }
        assertFailsWith<NullPointerException> {
            productService.handleSearchProducts(null)
        }
    }
    @Test
    fun testServiceFetchProducts() :Unit= runBlocking {
        productRepo.getAllProducts()
        productService.handleFetchProducts("smartphones").apply {
            assertEquals(3,this[2].id)
        }
        productService.handleFetchProducts("mobiles").apply {
            assertEquals(emptyList(),this)
        }
        assertFailsWith<NullPointerException> {
            productService.handleFetchProducts(" ")
        }
    }
    @Test
    fun testServiceDeleteProducts():Unit= runBlocking {
        productRepo.getAllProducts()
        productService.handleDeleteProduct("laptops").apply {
            assertEquals(ResponseMessage("Deleted Successfully",true),this)
        }
        productService.handleDeleteProduct("mobiles").apply {
            assertEquals(ResponseMessage("Failed to delete data",false),this)
        }
        assertFailsWith<NullPointerException> {
            productService.handleDeleteProduct(null)
        }
    }
    @Test
    fun testServiceUpdateProducts():Unit= runBlocking {
        productRepo.getAllProducts()
        productService.handleUpdateProduct(2,"iPhone 14").apply {
            assertEquals(ResponseMessage("Updated Successfully",true),this)
        }
        productService.handleUpdateProduct(231,"Mac").apply {
            assertEquals(ResponseMessage("Failed to update data",false),this)
        }
        assertFailsWith<NullPointerException> {
            productService.handleUpdateProduct(1,null)
        }
    }


















}