package com.example.farmerapp.presentation.addProduct

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.use_case.product.remote.AddProductToApiUseCase
import com.example.farmerapp.domain.use_case.product.local.InsertProductUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddProductApiAfterSaveLocalUseCase
@Inject constructor(
    private val insertProductUseCase: InsertProductUseCase,
    private val addProductToApiUseCase: AddProductToApiUseCase
) {
    fun addProductApiAfterLocal(product: Product) =
        addProductToApiUseCase.addProduct(product)
            .filter { it is Resource.Success || it is Resource.Error }
            .map {
                if (it != null)
                    product.apiId = it.data!!.apiId

            }.flatMapMerge {
                insertProductUseCase.insertProdcut(product)
            }
}
