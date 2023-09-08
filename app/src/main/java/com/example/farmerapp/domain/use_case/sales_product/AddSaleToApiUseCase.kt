package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.data.remote.dto.SaleApiDto
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.api.repository.SaleApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleApiDto
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProduct
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProductDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddSaleToApiUseCase
@Inject constructor(
    private val saleApiRepository: SaleApiRepository
){
    fun addSale(salesProduct: SalesProduct)= flow<Resource<SalesProduct>>{
        emit(Resource.Loading())
        val saleApiDto = salesProduct.toSaleApiDto()
        val saleApiDtoToJson =Gson().toJson(saleApiDto)
        val sale = saleApiRepository.addSale(saleApiDtoToJson).toSalesProduct()
        emit(Resource.Success(sale))
    }.catch { emit(Resource.Error(it.message!!)) }
}