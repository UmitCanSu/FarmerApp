package com.example.farmerapp.data.remote.dto

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Customer
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

data class SaleApiDto(
    var id: String,
    var company: CompanyApiDto,
    var product: ProductApiDto,
    var customer: Customer,
    var farmer: FarmerApiDto,
    var price: Float,
    var location: LatLng,
    var productCount: Int,
    var saleDate: LocalDateTime,
    var locationDescription: String,
    var isPaid: Boolean,
    var amountPaids: List<AmountPaid>
        )
