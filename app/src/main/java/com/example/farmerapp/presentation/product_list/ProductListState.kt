package com.example.farmerapp.presentation.product_list

import com.example.farmerapp.domain.model.Product

sealed class ProductListState {
    object Idle : ProductListState()
    object Loading : ProductListState()
    data class Error(val errorMessage:String):ProductListState()
    data class ProductList(val productList: List<Product>) : ProductListState()
}