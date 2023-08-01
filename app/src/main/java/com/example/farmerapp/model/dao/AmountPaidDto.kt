package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.model.AmountPaid
import com.example.farmerapp.model.SalesProduct

@Dao
interface AmountPaidDto {
    @Insert
    fun insertAmountPaid(amountPaid: AmountPaid)

    @Update
    fun updateAmountPaid(amountPaid: AmountPaid)

    @Delete
    fun deleteAmountPaid(amountPaid: AmountPaid)

    @Query("Select * from AmountPaid where salesProduct=id")
    fun selectAmountPaidWithSalesProductId(salesProductId: Int)




}