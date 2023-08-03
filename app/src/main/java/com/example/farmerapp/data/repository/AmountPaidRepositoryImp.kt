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
    override suspend fun insertAmountPaid(amountPaid: AmountPaid):Long {
        return amountPaidDao.insertAmountPaid(amountPaid)
    }

    override suspend fun updateAmountPaid(amountPaid: AmountPaid):Int {
        return amountPaidDao.updateAmountPaid(amountPaid)
    }

    override suspend fun deleteAmountPaid(amountPaid: AmountPaid):Int {
        return amountPaidDao.deleteAmountPaid(amountPaid)
    }

    override suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidRelations> {
        return amountPaidDao.selectAmountPaidWithSalesProductId(salesProductId)
    }
}