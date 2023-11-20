package com.example.farmerapp.presentation.addProduct

sealed class ProductInsertAndUpdateState {
    object Idle : ProductInsertAndUpdateState()
    object Loading : ProductInsertAndUpdateState()
    data class Error(val errorMessage:String):ProductInsertAndUpdateState()
    data class Success(val isSuccessfully: Boolean) : ProductInsertAndUpdateState()
}