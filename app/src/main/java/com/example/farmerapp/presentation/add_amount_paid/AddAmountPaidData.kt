package com.example.farmerapp.presentation.add_amount_paid

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.SalesProduct
import java.time.LocalDateTime

data class AddAmountPaidData(
    val salesProduct: SalesProduct? = null,
    val customerList: List<Customer> = emptyList(),
    val amountPaid: List<AmountPaid> = emptyList(),
    val selectedCustomer: Customer? = null,
    val remainingDept: Float = 0f,
    val enterPrice: Float = 0f,
    val date: LocalDateTime? = null,
    val location: Float = 0f

)