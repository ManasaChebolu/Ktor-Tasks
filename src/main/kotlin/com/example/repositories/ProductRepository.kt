package com.example.repositories

import com.example.database.DatabaseFactory
import com.example.database.ProductsEntity
import com.example.models.ProductsInterface
import com.example.plugins.getData
import com.example.models.Products
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductRepository : ProductsInterface {
    private fun resultToRow(row: ResultRow) = Products(
        id = row[ProductsEntity.id],
        title = row[ProductsEntity.title],
        description = row[ProductsEntity.description],
        price = row[ProductsEntity.price],
        discountPercentage = row[ProductsEntity.discountPercentage],
        rating = row[ProductsEntity.rating],
        stock = row[ProductsEntity.stock],
        brand = row[ProductsEntity.brand],
        category = row[ProductsEntity.category],
        thumbnail = row[ProductsEntity.thumbnail],
        images = mutableListOf(row[ProductsEntity.images])
        )
    override suspend fun getAllProducts():Unit {
        val productData= getData()
       return DatabaseFactory.dbQuery {
           for(product in productData.products) {
            ProductsEntity.insert {
                it[id]=product.id
                it[title]=product.title
                it[description] = product.description
                it[price] = product.price
                it[discountPercentage] = product.discountPercentage
                it[rating] = product.rating
                it[stock] = product.stock
                it[brand] = product.brand
                it[category] = product.category
                it[thumbnail] = product.thumbnail
                it[images] = product.images.toString()
            }
           }
        }
    }
    override suspend fun searchProducts(query:String): List<Products> =
        DatabaseFactory.dbQuery {
        ProductsEntity
            .select{(ProductsEntity.title eq query) or (ProductsEntity.description eq query)}
            .map(::resultToRow)
    }

    override suspend fun fetchProductsByCategory(category:String): List<Products> =
        DatabaseFactory.dbQuery {
            ProductsEntity
                .select{ ProductsEntity.category eq category}
                .map(::resultToRow)
    }

    override suspend fun deleteProductsByCategory(category: String): Int =
        DatabaseFactory.dbQuery {
            ProductsEntity.deleteWhere { ProductsEntity.category eq category }
        }


    override suspend fun updateProduct(id:Int,title:String): Int =
        DatabaseFactory.dbQuery{
            ProductsEntity
                .update({ProductsEntity.id eq id}){
                it[ProductsEntity.title]=title
            }
    }

}