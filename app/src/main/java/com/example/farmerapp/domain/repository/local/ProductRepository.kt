package com.example.farmerapp.domain.repository.local

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations


interface ProductRepository {
    suspend fun insertProduct(productDto: ProductDto):Long
    suspend fun updateProduct(productDto: ProductDto):Int
    suspend fun deleteProduct(productDto: ProductDto):Int

    suspend fun selectProductWithId(productId: Int):ProductRelations
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<ProductRelations>
    suspend fun getAllProductList(): List<ProductRelations>


}