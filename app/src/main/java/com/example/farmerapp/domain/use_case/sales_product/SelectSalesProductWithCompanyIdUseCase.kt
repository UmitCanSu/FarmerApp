package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.room.SaleProductRepository
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectSalesProductWithCompanyIdUseCase
@Inject constructor(
    private val salesProductRepository: SaleProductRepository
) {
    fun selectSalesProductWithCompanyId(companyId: Int) = flow<Resource<List<SalesProduct>>> {
        emit(Resource.Loading())
        val salesProducts = salesProductRepository.selectSalesProductWithCompanyId(companyId)
            .map {
                it.toSaleProduct()
            }
            .ifEmpty { emptyList() }
        emit(Resource.Success(salesProducts))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}