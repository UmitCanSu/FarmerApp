package com.example.farmerapp.domain.model

import com.example.farmerapp.until.Sesion
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime
import javax.inject.Inject


data class SalesProduct
@Inject constructor(
    var id: Int,
    var apiId:String,
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
    var amountPaint: List<AmountPaid>
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
        Sesion.getInstance().company!!,
        Sesion.getInstance().farmer!!,
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
        "",
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