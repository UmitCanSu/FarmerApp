package com.example.farmerapp.until

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.dao.CompanyDao
import com.example.farmerapp.domain.repository.dao.CustomerDao
import com.example.farmerapp.domain.repository.dao.FarmerDao

@Database(entities = [Company::class, Customer::class, Farmer::class, Product::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun companyDao(): CompanyDao
    abstract fun farmerDao(): FarmerDao
    abstract fun customerDao(): CustomerDao

}