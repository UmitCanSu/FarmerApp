package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.remote.dto.ProductApiDto

interface ProductApiRepository {
    suspend fun addProduct(productJson:String):ProductApiDto
    suspend fun getProductListByCompanyId(companyId:String):List<ProductApiDto>
}