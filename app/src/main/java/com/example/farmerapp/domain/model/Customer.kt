package com.example.farmerapp.domain.model

import com.example.farmerapp.until.FarmerStatus
import javax.inject.Inject

data class Customer
@Inject constructor(
    var id: Int = 0,
    var company: Company,
    var name: String,
    var sourName: String,
    var years: Int,
    var farmerStatus: FarmerStatus,
    var apiId: String,
) {
    constructor(
        company: Company,
        name: String,
        sourName: String,
        years: Int,
        farmerStatus: FarmerStatus,
    ) : this(0, company,name, sourName,years ,farmerStatus , "")
}
