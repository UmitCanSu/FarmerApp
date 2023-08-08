package com.example.farmerapp.domain.use_case.default_data

import android.util.Log
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.use_case.company.SelectCompanyWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.product.InsertProductUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.UnitType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultSaveProductUseCase
@Inject constructor(
    private val insertProduct: InsertProductUseCase
) {
    fun saveDefaultProduct(company: Company) = flow<Resource<Boolean>> {
        val defaultProduct = ArrayList<Product>()
        defaultProduct.add(Product("Balya Yapma", UnitType.AD.name, 30, company))
        defaultProduct.add(Product("Süt", UnitType.AD.name, 150, company))
        defaultProduct.add(Product("Balya", UnitType.AD.name, 100, company))
        defaultProduct.add(Product("Fasulye", UnitType.AD.name, 30, company))
        for (product in defaultProduct) {
            val index = insertProduct.insertProdcut(product).collect()
            Log.e("S->", "index" + index)
        }
        emit(Resource.Success(true))
    }.catch {
        emit(Resource.Error(it.message!!))
    }.flowOn(Dispatchers.IO)
}