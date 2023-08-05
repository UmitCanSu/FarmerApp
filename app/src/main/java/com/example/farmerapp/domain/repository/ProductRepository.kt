package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product


interface ProductRepository {
    suspend fun insertProduct(productDto: ProductDto):Long
    suspend fun updateProduct(productDto: ProductDto):Int
    suspend fun deleteProduct(productDto: ProductDto):Int
    suspend fun selectProductWithId(productId: Int):ProductRelations
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<ProductRelations>
    suspend fun getAllProductList(): List<ProductRelations>
}