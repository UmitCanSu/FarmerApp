package com.example.farmerapp.presentation.add_amount_paid

sealed class AddAmountPaidFragmentOnEvent {
    data class SaveAmountPaid(val selectedCustomerIndex: Int, val price: Float) :
        AddAmountPaidFragmentOnEvent()

    data class GetSalesProduct(val salesProductId: Int) : AddAmountPaidFragmentOnEvent()
    data class CalculateAmountPaid(val salesProductId: Int) : AddAmountPaidFragmentOnEvent()
}