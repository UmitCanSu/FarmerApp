package com.example.farmerapp.domain.model

import javax.inject.Inject


data class Company
@Inject constructor(
    var id: Int ,
    val name: String,
    val address: String,
    val phone: String,
    val apiId:String
) {


    constructor(
        name: String,
        address: String,
        phone: String,
        apiId: String
    ) : this(0, name, address, phone,apiId)

    constructor(
        name: String,
        address: String,
        phone: String
    ) : this(0, name, address, phone,"")
}