package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.remote.dto.SaleApiDto
import retrofit2.http.Query

interface SaleApiRepository {
    suspend fun addSale(saleJson: String): SaleApiDto
    suspend fun getSaleListByCompanyId(companyId: String): List<SaleApiDto>
    suspend fun addAmountPaid(saleId: String, amountPaidJson: String): SaleApiDto
    suspend fun getSale(saleId: String, ): SaleApiDto
}