package com.example.farmerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import javax.inject.Inject

@Entity
data class SalesProduct
@Inject constructor(
    val company: Company,
    val product: Product,
    val customer: Customer,
    val farmer: Farmer,
    val price: Int,
    val isDept: Boolean,
    val productNumber: Int,
    val location: String,
    val salesDate: Date,
    val amountPaint: List<AmountPaid>
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
