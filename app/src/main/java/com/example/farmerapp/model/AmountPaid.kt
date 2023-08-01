package com.example.farmerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import javax.inject.Inject

@Entity
data class AmountPaid
@Inject constructor(
    val salesProduct: SalesProduct,
    val price: Int,
    val date: Date,
    val customer: Customer,
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
