package com.example.farmerapp.presentation.add_amount_paid

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer

sealed class AddAmountPaidFragmentState {
    object Idle : AddAmountPaidFragmentState()
    object Loading : AddAmountPaidFragmentState()
    data class CustomerList(val customerList: List<Customer>) : AddAmountPaidFragmentState()
    data class IsSavedAmountPaid(val isSave: Boolean) : AddAmountPaidFragmentState()
    data class RemainingDept(val remainingDept: Float) : AddAmountPaidFragmentState()
    data class Error(val errorMessage: String) : AddAmountPaidFragmentState()

}
