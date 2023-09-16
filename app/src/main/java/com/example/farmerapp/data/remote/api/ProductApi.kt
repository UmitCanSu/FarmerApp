package com.example.farmerapp.data.remote.api

import com.example.farmerapp.data.remote.dto.ProductApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {
    @POST("Product/AddProduct")
    suspend fun addProduct(
        @Query("productJson") productJson: String
    ): ProductApiDto

    @POST("Product/GetProductByCompanyId")
    suspend fun getProductListByCompanyId(
        @Query("companyId") companyId: String
    ): List<ProductApiDto>
}