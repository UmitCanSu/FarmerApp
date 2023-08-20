package com.example.farmerapp.presentation.farmer_insert_and_update

import com.example.farmerapp.domain.model.Farmer

sealed class FarmerFragmentOnEvent {
    data class insertFarmer(val farmer:Farmer):FarmerFragmentOnEvent()
    data class updateFarmer(val farmer:Farmer):FarmerFragmentOnEvent()
}