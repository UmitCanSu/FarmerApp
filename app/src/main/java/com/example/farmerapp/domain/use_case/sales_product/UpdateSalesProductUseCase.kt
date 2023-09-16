package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProductDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateSalesProductUseCase
@Inject constructor(
    private val saleProductRepository: SaleProductRepository
) {
    fun updateSalesProduct(salesProduct: SalesProduct) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updatedIndex = saleProductRepository.updateSaleProduct(salesProduct.toSalesProductDto())
        emit(Resource.Success(updatedIndex > 0))
    }.catch {
        emit(Resource.Error(it.message!!))
    }
}