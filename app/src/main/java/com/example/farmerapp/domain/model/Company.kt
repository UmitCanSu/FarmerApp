package com.example.farmerapp.domain.model

import javax.inject.Inject


data class Company
@Inject constructor(
    val name: String,
    val address: String,
    val phone: String
){
    var id: Int = 0
}