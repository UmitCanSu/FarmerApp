package com.example.farmerapp.presentation.sale_list_fragment

sealed class SaleListOnEvent {
    data class SaleList(val companyId: Int) : SaleListOnEvent()
    data class IsPaid(val isPaid: Boolean) : SaleListOnEvent()
}