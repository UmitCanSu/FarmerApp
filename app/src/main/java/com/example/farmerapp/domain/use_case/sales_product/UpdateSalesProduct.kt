package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.SaleProductRepository
import com.example.farmerapp.until.Extetensions.SalesProductExtensions.toSalesProductDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateSalesProduct
@Inject constructor(
    private val saleProductRepository: SaleProductRepository
) {
    fun updateSalesProduct(salesProduct: SalesProduct) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        saleProductRepository.updateSaleProduct(salesProduct.toSalesProductDto())
        emit(Resource.Success(true))
    }.catch {
        emit(Resource.Error(it.message))
    }
}