package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.domain.model.Customer

@Dao
interface CustomerDao {
    @Insert
    suspend  fun insertCustomer(customer: Customer)

    @Update
    suspend  fun updateCustomer(customer: Customer)

    @Query("Select * from Customer where id=:customerId")
    suspend  fun selectCustomerWithCustomerID(customerId: Int): Customer


}