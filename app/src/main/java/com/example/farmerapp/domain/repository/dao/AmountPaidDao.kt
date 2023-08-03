package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid

@Dao
interface AmountPaidDao {
    @Insert
   suspend fun insertAmountPaid(amountPaid: AmountPaid):Long

    @Update
    suspend  fun updateAmountPaid(amountPaid: AmountPaid):Int

    @Delete
    suspend fun deleteAmountPaid(amountPaid: AmountPaid):Int

    @Query("Select * from AmountPaid where salesProduct=id")
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int):List<AmountPaidRelations>




}