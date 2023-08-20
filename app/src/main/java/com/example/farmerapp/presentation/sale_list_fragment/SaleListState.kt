package com.example.farmerapp.presentation.sale_list_fragment

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.presentation.sale_fragment.SaleFragmentState

sealed class SaleListState {
    object Idle : SaleListState()
    object Loading : SaleListState()
    data class SaleList(val salesProductList: List<SalesProduct>) : SaleListState()
    data class Error(val errorMessage: String) : SaleListState()
}