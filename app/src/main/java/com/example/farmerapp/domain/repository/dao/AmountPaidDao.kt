package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations

@Dao
interface AmountPaidDao {
    @Insert
    suspend fun insertAmountPaid(amountPaid: AmountPaidDto): Long

    @Update
    suspend fun updateAmountPaid(amountPaid: AmountPaidDto): Int

    @Delete
    suspend fun deleteAmountPaid(amountPaid: AmountPaidDto): Int


    @Query("Select * from AmountPaid where salesProductId=:salesProductId")
    @Transaction
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int): List<AmountPaidRelations>

    @Query("select (Select price from SalesProduct where id = :salesProductId) -(Select sum(price) from AmountPaid where salesProductId = :salesProductId) as enterPrice")
    suspend fun getRemainingDept(salesProductId: Int): Float?


}