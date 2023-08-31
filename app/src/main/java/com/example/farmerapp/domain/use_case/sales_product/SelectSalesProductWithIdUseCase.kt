package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.room.SaleProductRepository
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectSalesProductWithIdUseCase @Inject constructor(
    private val saleProductRepository: SaleProductRepository
) {
    fun selectSalesProductWithId(salesProductId: Int) = flow<Resource<SalesProduct>> {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                saleProductRepository.selectSaleProductWithId(salesProductId).toSaleProduct()
            )
        )
    }.catch {
        emit(Resource.Error(it.message!!))
    }


}


