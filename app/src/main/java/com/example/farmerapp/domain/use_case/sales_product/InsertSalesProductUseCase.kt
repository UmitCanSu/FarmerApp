package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.room.SaleProductRepository
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProductDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSalesProductUseCase
@Inject constructor(
    private val saleProductRepository: SaleProductRepository
) {
    fun insertSaleProduct(salesProduct: SalesProduct) = flow {
        emit(Resource.Loading())
        val id = saleProductRepository.insertSaleProduct(salesProduct.toSalesProductDto())
        emit(Resource.Success(id))
    }.catch {
        emit(Resource.Error(it.message!!))
    }

}