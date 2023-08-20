package com.example.farmerapp.domain.model

import javax.inject.Inject


data class Company
@Inject constructor(
    var id: Int = 0,
    val name: String,
    val address: String,
    val phone: String
) {


    constructor(
        name: String,
        address: String,
        phone: String
    ) : this(0, name, address, phone)
}