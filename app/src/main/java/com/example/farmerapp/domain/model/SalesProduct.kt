package com.example.farmerapp.domain.model

import com.example.farmerapp.until.UserSingleton
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime
import javax.inject.Inject


data class SalesProduct
@Inject constructor(
    val id: Int,
    val company: Company,
    val farmer: Farmer,
    val product: Product,
    val customer: Customer,
    val price: Float,
    val isDept: Boolean,
    val productNumber: Int,
    val location: LatLng,
    val locationDescription: String,
    val salesDate: LocalDateTime,
    var isPaid: Boolean,
    val amountPaint: List<AmountPaid>
) {
    constructor(
        product: Product,
        customer: Customer,
        price: Float,
        isDept: Boolean,
        productNumber: Int,
        location: LatLng,
        locationDescription: String,
        salesDate: LocalDateTime,
        isPaid: Boolean,
        amountPaint: List<AmountPaid>
    ) : this(
        UserSingleton.getInstance().company!!,
        UserSingleton.getInstance().farmer!!,
        product,
        customer,
        price,
        isDept,
        productNumber,
        location,
        locationDescription,
        salesDate,
        isPaid,
        amountPaint
    )
    constructor(
        company: Company,
        farmer: Farmer,
        product: Product,
        customer: Customer,
        price: Float,
        isDept: Boolean,
        productNumber: Int,
        location: LatLng,
        locationDescription: String,
        salesDate: LocalDateTime,
        isPaid: Boolean,
        amountPaint: List<AmountPaid>
    ) : this(
        0,
        company,
        farmer,
        product,
        customer,
        price,
        isDept,
        productNumber,
        location,
        locationDescription,
        salesDate,
        isPaid,
        amountPaint
    )
}