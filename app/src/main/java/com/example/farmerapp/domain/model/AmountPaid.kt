package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import javax.inject.Inject

@Entity
data class AmountPaid
@Inject constructor(
    val salesProduct: SalesProduct,
    val customer: Customer,
    val price: Int,
    val date: Date,

) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
