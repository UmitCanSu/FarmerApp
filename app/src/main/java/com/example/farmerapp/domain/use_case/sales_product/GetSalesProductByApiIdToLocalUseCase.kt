package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSalesProductByApiIdToLocalUseCase
@Inject constructor(
    private val salesProductRepository: SaleProductRepository
) {
    fun getSalesProductByApiIdToLocal(salesProductApiId: String) = flow<Resource<SalesProduct?>> {
        val salesProduct = salesProductRepository.selectSaleProductByApiId(salesProductApiId)
        if (salesProduct != null)
            emit(Resource.Success(salesProduct.toSaleProduct()))
        else
            emit(Resource.Success(null))
    }.catch { emit(Resource.Error(it.message!!)) }
}