package com.example.farmerapp.presentation.sale_fragment

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct

sealed class SaleFragmentOnEvent {
    data class CalculatePrice(val productCount: Int) : SaleFragmentOnEvent()
    data class SelectProduct(val productIndex: Int) : SaleFragmentOnEvent()
}