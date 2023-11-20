package com.example.farmerapp.domain.model

import javax.inject.Inject


data class
Product
@Inject constructor(
    var id: Int,
    val name: String,
    val unitType: String,
    val price: Float,
    val company: Company,
    var apiId: String
) {

    constructor(
        name: String,
        unitType: String,
        price: Float,
        company: Company,
    ) : this(
        0,
        name, unitType, price, company,
        ""
    )

}
