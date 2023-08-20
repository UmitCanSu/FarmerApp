package com.example.farmerapp.presentation.sale_fragment

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Product

/*
data class SaleFragmentState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val amountPrice: Int? = 0,
    val errorMessage: String? = "",
    val productList: List<Product>? = emptyList(),
    val customerList: List<Customer>? = emptyList()
)
*/
sealed class SaleFragmentState {
    object Idle : SaleFragmentState()
    object Loading : SaleFragmentState()
    data class ProdcutList(val productList: List<Product>) : SaleFragmentState()
    data class CustomerList(val customerList: List<Customer>) : SaleFragmentState()
    data class SelectedProduct(val selectedProduct: Product) : SaleFragmentState()
    data class IsSavesSalesProduct(val salesId: Int) : SaleFragmentState()
    data class Calculate(val amountPrice: String) : SaleFragmentState()
    data class Error(val errorMessage: String) : SaleFragmentState()
}
