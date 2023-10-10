package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.ProductApiDto

interface ProductApiRepository {
    suspend fun addProduct(productJson:String):ProductApiDto
    suspend fun getProductListByCompanyId(companyId:String):List<ProductApiDto>
    suspend fun getProductListByFarmerId(farmerId:String):List<ProductApiDto>
}