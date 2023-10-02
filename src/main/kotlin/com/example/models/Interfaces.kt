package com.example.models

interface ProductsInterface {
    suspend fun getAllProducts():Any
    suspend fun searchProducts(query:String):List<Products>
    suspend fun fetchProductsByCategory(category: String):List<Products>
    suspend fun deleteProductsByCategory(category: String):Int
    suspend fun updateProduct(id:Int,title:String):Int
}