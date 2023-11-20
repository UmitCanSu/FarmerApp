package com.example.farmerapp.presentation.customer_fragment

import com.example.farmerapp.domain.model.Customer

sealed class CustomerFragmentOnEvent {
    data class SavedCustomer(val customer: Customer) : CustomerFragmentOnEvent()
    data class FindCustomerByPhoneNumber(val phoneNumber: String) : CustomerFragmentOnEvent()
    data class FindCustomerByFarmerId(val phoneOrCustomerNumber: String) : CustomerFragmentOnEvent()
}