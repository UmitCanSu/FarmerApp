package com.example.farmerapp.domain.model

import java.sql.Date

data class Impregnation(
    val id: Int,
    val mother: Animal,
    val father: Animal?,
    val company: Company,
    val farmer: Farmer,
    val date: Date,
    val veterinarian:String
)