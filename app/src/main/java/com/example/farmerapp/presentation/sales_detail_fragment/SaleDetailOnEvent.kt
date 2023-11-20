package com.example.farmerapp.presentation.sales_detail_fragment

sealed interface SaleDetailOnEvent {
    data class SelectProduct(val salesProductID: Int, val salesProductApiId: String) :
        SaleDetailOnEvent
}