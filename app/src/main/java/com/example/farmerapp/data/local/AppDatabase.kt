package com.example.farmerapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.dto.LoginLocalDto
import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.domain.repository.dao.AmountPaidDao
import com.example.farmerapp.domain.repository.dao.AnimalDao
import com.example.farmerapp.domain.repository.dao.CompanyDao
import com.example.farmerapp.domain.repository.dao.CustomerDao
import com.example.farmerapp.domain.repository.dao.FarmerDao
import com.example.farmerapp.domain.repository.dao.LoginDao
import com.example.farmerapp.domain.repository.dao.ProductDao
import com.example.farmerapp.domain.repository.dao.SaleProductDao

@Database(
    entities = [
        CompanyDto::class,
        CustomerDto::class,
        FarmerDto::class,
        ProductDto::class,
        SalesProductDto::class,
        AmountPaidDto::class,
        AnimalDto::class,
        LoginLocalDto::class
    ], version = 1
)
@TypeConverters(DbConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun amountPaid(): AmountPaidDao
    abstract fun companyDao(): CompanyDao
    abstract fun customerDao(): CustomerDao
    abstract fun farmerDao(): FarmerDao
    abstract fun productDao(): ProductDao
    abstract fun salesProductDao(): SaleProductDao
    abstract fun animalDao(): AnimalDao
    abstract fun loginDao(): LoginDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "farmer.db"
            ).build()
        }
    }


}