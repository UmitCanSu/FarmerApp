package com.example.farmerapp.data.remote.dto
data class CustomerApiDto(
    val farmer:FarmerApiDto,
    val saved: SavedApiDto?
)