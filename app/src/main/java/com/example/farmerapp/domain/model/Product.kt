package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject


data class Product
@Inject constructor(
    var id: Int = 0,
    val name: String,
    val unitType: String,
    val price: Int,
    val company: Company
) {

    constructor(
        name: String,
        unitType: String,
        price: Int,
        company: Company
    ) : this(
        0,
        name, unitType, price, company
    )

}
