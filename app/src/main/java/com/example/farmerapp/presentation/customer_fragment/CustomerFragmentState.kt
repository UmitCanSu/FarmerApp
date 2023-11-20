package com.example.farmerapp.presentation.customer_fragment

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.Resource
import javax.inject.Inject

sealed interface CustomerFragmentState {
    object Idle : CustomerFragmentState
    object Loading : CustomerFragmentState
    data class FindCustomer(val customer: Customer) : CustomerFragmentState
    data class SaveCustomer(val isSaved: Boolean) : CustomerFragmentState
    data class Error(val errorMessage: String) : CustomerFragmentState
}