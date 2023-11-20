package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProduct
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProductDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSaleListToApiUseCase
@Inject constructor(
    private val saleApiRepository: SaleApiRepository
) {
    fun getSaleListToApi(companyId: String) = flow<Resource<List<SalesProduct>>> {
        emit(Resource.Loading())
        val saleApiDtoList = saleApiRepository.getSaleListByCompanyId(companyId)
        val saleList = saleApiDtoList.map {it.toSalesProduct()}
        emit(Resource.Success(saleList))
    }.catch { emit(Resource.Error(it.message!!)) }
}