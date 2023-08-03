package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid

interface AmountPaidRepository {
    suspend fun insertAmountPaid(amountPaid: AmountPaid):Long
    suspend fun updateAmountPaid(amountPaid: AmountPaid):Int
    suspend fun deleteAmountPaid(amountPaid: AmountPaid):Int
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidRelations>
}