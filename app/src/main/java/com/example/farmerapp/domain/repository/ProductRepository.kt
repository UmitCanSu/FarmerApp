package com.example.farmerapp.domain.repository

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product


interface ProductRepository {
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun selectProductWithId(productId: Int)
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<Farmer>
}