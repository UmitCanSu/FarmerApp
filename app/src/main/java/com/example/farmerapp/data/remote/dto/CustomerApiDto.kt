package com.example.farmerapp.data.remote.dto

data class CustomerApiDto(
    val customer: FarmerApiDto,
    val saved: SavedApiDto
)