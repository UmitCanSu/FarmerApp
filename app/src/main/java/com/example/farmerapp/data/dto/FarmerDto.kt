package com.example.farmerapp.data.dto

import javax.inject.Inject

data class FarmerDto
@Inject constructor(
    val companyId: Int,
    val name: String,
    val sourName: String,
    val years: String,
    val farmerIndex: Int
)

