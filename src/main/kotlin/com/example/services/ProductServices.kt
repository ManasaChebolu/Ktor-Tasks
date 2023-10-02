package com.example.services

import com.example.models.Products
import com.example.models.ResponseMessage
import com.example.repositories.ProductRepository

import java.lang.NullPointerException

class ProductServices {
     private val productRepository = ProductRepository()
    suspend fun handleGetAllProducts():Unit {
        return try {
            productRepository.getAllProducts()
        }catch (e:Exception) {
            throw InternalError()
        }
    }

    suspend fun handleSearchProducts(query: String?): List<Products> {
        return if(query.isNullOrBlank())
            throw NullPointerException()
        else
         productRepository.searchProducts(query)
    }

    suspend fun handleFetchProducts(query: String?): List<Products> {
        return if(query.isNullOrBlank())
                throw NullPointerException()
        else
            productRepository.fetchProductsByCategory(query)
    }

    suspend fun handleDeleteProduct(query: String?): ResponseMessage {
        return if(query.isNullOrBlank())
            throw NullPointerException()
        else if(productRepository.deleteProductsByCategory(query)>=1)
            ResponseMessage("Deleted Successfully",true)
        else
            ResponseMessage("Failed to delete data",false)
    }
    suspend fun handleUpdateProduct(id:Int,title:String?) :ResponseMessage{
        return if(title.isNullOrBlank())
            throw NullPointerException()
        else if(productRepository.updateProduct(id, title)>=1)
            ResponseMessage("Updated Successfully",true)
        else
            ResponseMessage("Failed to update data",false)
    }
}