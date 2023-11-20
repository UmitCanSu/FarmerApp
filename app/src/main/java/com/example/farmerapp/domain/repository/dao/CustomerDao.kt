package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.farmerapp.data.local.dto.CustomerDto

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertCustomer(customer: CustomerDto): Long

    @Update
    suspend fun updateCustomer(customer: CustomerDto): Int

    @Query("Select * from Customer where id=:customerId")
    suspend fun selectCustomerWithCustomerID(customerId: Int): CustomerDto


    @Query("SELECT * FROM Customer")
    @Transaction
    suspend fun getAllCustomer(): List<CustomerDto>

    @Query("SELECT * FROM Customer where customerApiId =:apiId")
    suspend fun getCustomerByApiId(apiId: String): CustomerDto


}