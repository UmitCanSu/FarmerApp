package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid

interface AmountPaidRepository {
    suspend fun insertAmountPaid(amountPaidDto: AmountPaidDto):Long
    suspend fun updateAmountPaid(amountPaidDto: AmountPaidDto):Int
    suspend fun deleteAmountPaid(amountPaidDto: AmountPaidDto):Int
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidRelations>
    suspend fun getRemainingDept(salesProductId: Int):Int
}