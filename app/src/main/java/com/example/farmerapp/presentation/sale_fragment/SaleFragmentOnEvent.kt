package com.example.farmerapp.presentation.sale_fragment

sealed class SaleFragmentOnEvent {
    data class CalculatePrice(val productCount: Int) : SaleFragmentOnEvent()
    data class SelectProduct(val productIndex: Int) : SaleFragmentOnEvent()
    data class SelectCustomer(val customerIndex: Int):SaleFragmentOnEvent()
    data class isDept(val isCheck:Boolean):SaleFragmentOnEvent()
    object  Save:SaleFragmentOnEvent()
}