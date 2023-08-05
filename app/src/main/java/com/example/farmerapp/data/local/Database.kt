package com.example.farmerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.dao.AmountPaidDao
import com.example.farmerapp.domain.repository.dao.CompanyDao
import com.example.farmerapp.domain.repository.dao.CustomerDao
import com.example.farmerapp.domain.repository.dao.FarmerDao
import com.example.farmerapp.domain.repository.dao.ProductDao
import com.example.farmerapp.domain.repository.dao.SaleProductDao

@Database(entities = [Company::class, Customer::class, Farmer::class, Product::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun amountPaid(): AmountPaidDao
    abstract fun companyDao(): CompanyDao
    abstract fun customerDao(): CustomerDao
    abstract fun farmerDao(): FarmerDao
    abstract fun productDao(): ProductDao
    abstract fun salesProductDao(): SaleProductDao


}