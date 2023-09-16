package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.SaleApiDto

interface SaleApiRepository {
    suspend fun addSale(saleJson: String): SaleApiDto
    suspend fun getSaleListByCompanyId(companyId: String): List<SaleApiDto>
    suspend fun addAmountPaid(saleId: String, amountPaidJson: String): SaleApiDto
    suspend fun getSale(saleId: String, ): SaleApiDto
}