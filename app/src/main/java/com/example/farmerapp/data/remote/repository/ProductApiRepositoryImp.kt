package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.dto.ProductApiDto
import com.example.farmerapp.domain.repository.api.ProductApi
import com.example.farmerapp.domain.repository.api.ProductApiRepository
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
}