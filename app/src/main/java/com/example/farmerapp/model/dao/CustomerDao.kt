package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDao {
    @Insert
    fun insertCustomer(customer: CustomerDao)

    @Update
    fun updateCustomer(customer: CustomerDao)

    @Query("Select * from Customer where id=:customerId")
    fun selectCustomerWithCustomerID(customerId: Int): CustomerDao


}