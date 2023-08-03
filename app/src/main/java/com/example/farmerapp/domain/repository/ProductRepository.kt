package com.example.farmerapp.domain.repository

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product


interface ProductRepository {
    suspend fun insertProduct(product: Product):Long
    suspend fun updateProduct(product: Product):Int
    suspend fun deleteProduct(product: Product):Int
    suspend fun selectProductWithId(productId: Int):Product
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<Farmer>
}