package com.example.farmerapp.domain.use_case.sales_product.view

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.sales_product.AddSaleToApiUseCase
import com.example.farmerapp.domain.use_case.sales_product.InsertSalesProductToLocalUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaveApiAfterLocalUseCase
@Inject constructor(
    private val addSaleToApiUseCase: AddSaleToApiUseCase,
    private val insertSalesProductUseCase: InsertSalesProductToLocalUseCase
) {
    fun saveApiAfterLocal(salesProduct: SalesProduct) = addSaleToApiUseCase
        .addSale(salesProduct)
        .filterIsInstance<Resource.Success<SalesProduct>>()
        .map { it.data }
        .flatMapMerge {
            var salesProduct = salesProduct
            if (it != null) {
                salesProduct.apiId = it.apiId
            }
            insertSalesProductUseCase.insertSaleProduct(salesProduct)
        }


}