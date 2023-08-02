package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.dto.AmountPaidDto
import com.example.farmerapp.domain.model.AmountPaid

interface AmountPaidRepository {
    suspend fun insertAmountPaid(amountPaid: AmountPaid)
    suspend fun updateAmountPaid(amountPaid: AmountPaid)
    suspend fun deleteAmountPaid(amountPaid: AmountPaid)
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidDto>
}