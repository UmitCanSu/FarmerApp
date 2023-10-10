package com.example.farmerapp.data.remote.dto

import com.example.farmerapp.domain.model.AmountPaid
import com.google.android.gms.maps.model.LatLng

data class SaleApiDto(
    var id: String,
    var company: CompanyApiDto,
    var product: ProductApiDto,
    var customer: CustomerApiDto,
    var farmer: FarmerApiDto,
    var price: Float,
    var location: LatLng,
    var productCount: Int,
    var saleDate: String,
    var locationDescription: String,
    var isPaid: Boolean,
    var amountPaids: List<AmountPaid>
        )
