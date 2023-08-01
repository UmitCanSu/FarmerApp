package com.example.farmerapp.until

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.farmerapp.model.Company
import com.example.farmerapp.model.Customer
import com.example.farmerapp.model.Farmer
import com.example.farmerapp.model.Product
import com.example.farmerapp.model.dao.CompanyDao
import com.example.farmerapp.model.dao.CustomerDao
import com.example.farmerapp.model.dao.FarmerDao

@Database(entities = [Company::class, Customer::class, Farmer::class, Product::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun companyDao(): CompanyDao
    abstract fun farmerDao(): FarmerDao
    abstract fun customerDao(): CustomerDao

}