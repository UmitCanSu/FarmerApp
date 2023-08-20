package com.example.farmerapp.presentation.sales_detail_fragment

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.SalesProduct


sealed class SaleDetailState {
    object Idle : SaleDetailState()
    object Loading : SaleDetailState()
    data class Error(val errorMessage: String) : SaleDetailState()
    data class ShowSalesProduct(val salesProduct: SalesProduct) : SaleDetailState()
    data class ShowAmountPaidList(val amountPaidList: List<AmountPaid>) : SaleDetailState()
}