package com.example.farmerapp.presentation.farmer_insert_and_update

sealed class FarmerFragmentState {
    object Idle : FarmerFragmentState()
    object Loading : FarmerFragmentState()
    data class Error(val errorMessage:String):FarmerFragmentState()
    data class IsUpdate(val isUpdate:Boolean):FarmerFragmentState()
    data class IsInsert(val isInsert:Boolean):FarmerFragmentState()
}