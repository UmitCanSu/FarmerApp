package com.example.farmerapp.domain.use_case.product.remote

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.use_case.product.local.GetProductListByCompanyIdToLocalUseCase
import com.example.farmerapp.domain.use_case.product.local.InsertProductUseCase
import com.example.farmerapp.domain.use_case.product.local.GetProductByApiIdToLocalUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase
@Inject constructor(
    private val getProductListByCompanyIdToApiUseCase: GetProductListByCompanyIdToApiUseCase,
    private val getProductByApiIdToLocalUseCase: GetProductByApiIdToLocalUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val getProductListByCompanyIdToLocalUseCase: GetProductListByCompanyIdToLocalUseCase
) {
    fun getProductList(
        companyApiId: String,
        companyLocalId: Int
    ) = getProductListByCompanyIdToApiUseCase.getProductList(companyApiId)
        .filterIsInstance<Resource.Success<List<Product>>>()
        .mapNotNull { it.data }
        .map { productList ->
            productList.map { product ->
                getProductByApiIdToLocalUseCase.getProductByApiIdToLocal(product.apiId)
                    .filter { it is Resource.Success }
                    .map { it.data }
                    .map { it == null }
                    .map {
                        if (it)
                            insertProductUseCase.insertProdcut(product).collect()
                    }.collect()
            }
        }.flatMapMerge {
            getProductListByCompanyIdToLocalUseCase.getProductListWithCompanyId(companyLocalId)
        }.catch { emit(Resource.Error(it.message!!)) }



}

