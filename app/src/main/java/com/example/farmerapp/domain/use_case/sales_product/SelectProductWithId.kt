package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.data.repository.SaleProductRepositoryImp
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.SaleProductRepository
import com.example.farmerapp.until.Extensions.toSaleProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectProductWithId @Inject constructor(
    private val saleProductRepository: SaleProductRepository
) {
    fun selectProductWithId(salesProductId: Int) = flow<Resource<SalesProduct>> {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                saleProductRepository.selectSaleProductWithId(salesProductId).toSaleProduct()
            )
        )
    }.catch {
        emit(Resource.Error(it.message))
    }


}


