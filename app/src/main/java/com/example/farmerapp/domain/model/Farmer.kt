package com.example.farmerapp.domain.model

import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.UserSingleton
import javax.inject.Inject

data class Farmer
@Inject constructor(
    var id: Int = 0,
    var company: Company?,
    var name: String,
    var sourName: String,
    var years: Int,
    var farmerStatus: FarmerStatus
) {
    constructor(
        company: Company?,
        name: String,
        sourName: String,
        years: Int,
        farmerStatus: FarmerStatus
    ) : this(0, company, name, sourName, years, farmerStatus)

    constructor(
        name: String,
        sourName: String,
        years: Int,
        farmerStatus: FarmerStatus
    ) : this(0, UserSingleton.getInstance().company, name, sourName, years, farmerStatus)
}