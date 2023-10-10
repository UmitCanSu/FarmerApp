package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.dto.ProductApiDto
import com.example.farmerapp.data.remote.api.ProductApi
import com.example.farmerapp.domain.repository.remote.ProductApiRepository
import javax.inject.Inject

class ProductApiRepositoryImp
    @Inject constructor(
        private val productApi: ProductApi
    ): ProductApiRepository {
    override suspend fun addProduct(productJson: String): ProductApiDto {
        return productApi.addProduct(productJson)
    }

    override suspend fun getProductListByCompanyId(companyId: String): List<ProductApiDto> {
        return productApi.getProductListByCompanyId(companyId)
    }

    override suspend fun getProductListByFarmerId(farmerId: String): List<ProductApiDto> {
        return productApi.getProductListByFarmerId(farmerId)
    }
}