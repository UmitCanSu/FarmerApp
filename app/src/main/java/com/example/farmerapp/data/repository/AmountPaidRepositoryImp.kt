package com.example.farmerapp.data.repository

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.AmountPaidRepository
import com.example.farmerapp.domain.repository.dao.AmountPaidDao
import javax.inject.Inject

class AmountPaidRepositoryImp
@Inject constructor(
    private val amountPaidDao: AmountPaidDao
    ) : AmountPaidRepository {
    override suspend fun insertAmountPaid(amountPaidDto: AmountPaidDto):Long {
        return amountPaidDao.insertAmountPaid(amountPaidDto)
    }

    override suspend fun updateAmountPaid(amountPaidDto: AmountPaidDto):Int {
        return amountPaidDao.updateAmountPaid(amountPaidDto)
    }

    override suspend fun deleteAmountPaid(amountPaidDto: AmountPaidDto):Int {
        return amountPaidDao.deleteAmountPaid(amountPaidDto)
    }

    override suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidRelations> {
        return amountPaidDao.selectAmountPaidWithSalesProductId(salesProductId)
    }
}