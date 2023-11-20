package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSaleBySaleIdToApiUseCase
@Inject constructor(
    private val saleApiRepository: SaleApiRepository
) {
    fun getSalesBySaleId(saleId: String) = flow {
        emit(Resource.Loading())
        val salesProduct = saleApiRepository.getSale(saleId).toSalesProduct()
        emit(Resource.Success(salesProduct))
    }.catch { emit(Resource.Error(it.message!!)) }
}