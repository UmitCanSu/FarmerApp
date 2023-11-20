package com.example.farmerapp.data.remote.api

import com.example.farmerapp.data.remote.dto.SaleApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface SaleApi {
    @POST("Sale/AddSale")
    suspend fun addSale(
        @Query("saleJson") saleJson: String
    ): SaleApiDto

    @POST("Sale/GetSalesByCompanyId")
    suspend fun getSaleListByCompanyId(
        @Query("companyId") companyId: String
    ): List<SaleApiDto>

    @POST("Sale/AddAmountPaid")
    suspend fun addAmountPaid(
        @Query("saleId") saleId: String,
        @Query("amountPaidJson") amountPaidJson: String,
        @Query("isPaid") isPaid: Boolean
    ): SaleApiDto

    @POST("Sale/GetSale")
    suspend fun getSale(
        @Query("saleId") saleId: String,
    ): SaleApiDto
}