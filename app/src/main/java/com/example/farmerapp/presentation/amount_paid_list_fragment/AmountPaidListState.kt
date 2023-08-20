package com.example.farmerapp.presentation.amount_paid_list_fragment

sealed class AmountPaidListState {
    object Idle : AmountPaidListState()
    object Loading : AmountPaidListState()
    data class Error(val errorMessage: String) : AmountPaidListState()
    object SavedCompany : AmountPaidListState()
}