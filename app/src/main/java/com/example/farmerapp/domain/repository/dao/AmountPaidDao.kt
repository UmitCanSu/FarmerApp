package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.dto.AmountPaidDto
import com.example.farmerapp.domain.model.AmountPaid

@Dao
interface AmountPaidDao {
    @Insert
   suspend fun insertAmountPaid(amountPaid: AmountPaid)

    @Update
    suspend  fun updateAmountPaid(amountPaid: AmountPaid)

    @Delete
    suspend fun deleteAmountPaid(amountPaid: AmountPaid)

    @Query("Select * from AmountPaid where salesProduct=id")
    suspend fun selectAmountPaidWithSalesProductId(salesProductId: Int):List<AmountPaidDto>




}