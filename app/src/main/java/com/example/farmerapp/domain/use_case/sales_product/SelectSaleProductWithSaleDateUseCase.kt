package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.until.Mappers
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.sql.Date
import javax.inject.Inject

class SelectSaleProductWithSaleDateUseCase @Inject constructor(
    private val salesProductRepository: SaleProductRepository
)
{
    fun selectSaleProductWithSaleDate(date:Date)= flow<Resource<List<SalesProduct>>> {
        emit(Resource.Loading())
        val listSaleProductWitOtherClass  = salesProductRepository.selectSaleProductWithSaleDate(date.toString())
        val listSalesProduct = Mappers.salesProductOtherClassListToSaleProductList(listSaleProductWitOtherClass)
        emit(Resource.Success(listSalesProduct))
    }.catch {
        emit(Resource.Error(it.message!!))
    }
}