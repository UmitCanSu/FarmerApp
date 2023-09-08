package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.dto.SaleApiDto
import com.example.farmerapp.domain.repository.api.SaleApi
import com.example.farmerapp.domain.repository.api.repository.SaleApiRepository
import javax.inject.Inject

class SaleApiRepositoryImp
@Inject constructor(
    private val saleApi: SaleApi
) : SaleApiRepository {
    override suspend fun addSale(saleJson: String): SaleApiDto {
        return saleApi.addSale(saleJson)
    }

    override suspend fun getSaleListByCompanyId(companyId: String): List<SaleApiDto> {
        return saleApi.getSaleListByCompanyId(companyId)
    }

    override suspend fun addAmountPaid(saleId: String, amountPaidJson: String): SaleApiDto {
        return saleApi.addAmountPaid(saleId,amountPaidJson)
    }

    override suspend fun getSale(saleId: String): SaleApiDto {
        return saleApi.getSale(saleId)
    }
}