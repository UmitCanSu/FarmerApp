package com.example.farmerapp.presentation.productInsertAndUpdate

import com.example.farmerapp.presentation.customer_fragment.CustomerFragmentState

sealed class ProductInsertAndUpdateState {
    object Idle : ProductInsertAndUpdateState()
    object Loading : ProductInsertAndUpdateState()
    data class Error(val errorMessage:String):ProductInsertAndUpdateState()
    data class Success(val isSuccessfully: Boolean) : ProductInsertAndUpdateState()
}