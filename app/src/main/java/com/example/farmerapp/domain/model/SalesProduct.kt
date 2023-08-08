package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDateTime
import javax.inject.Inject


data class SalesProduct
@Inject constructor(
    val company: Company,
    val product: Product,
    val customer: Customer,
    val farmer: Farmer,
    val price: Int,
    val isDept: Boolean,
    val productNumber: Int,
    val location: Float,
    val locationDescription:String,
    val salesDate: LocalDateTime,
    val amountPaint: List<AmountPaid>
){
    val id: Int = 0
}