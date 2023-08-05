package com.example.farmerapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.farmerapp.data.local.Database
import com.example.farmerapp.data.repository.AmountPaidRepositoryImp
import com.example.farmerapp.data.repository.CompanyRepositoryImp
import com.example.farmerapp.data.repository.CustomerRepositoryImp
import com.example.farmerapp.data.repository.FarmerRepositoryImp
import com.example.farmerapp.data.repository.ProductRepositoryImp
import com.example.farmerapp.data.repository.SaleProductRepositoryImp
import com.example.farmerapp.domain.repository.AmountPaidRepository
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.domain.repository.CustomerRepository
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.domain.repository.SaleProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "farmer_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAmountPaidRepository(dataBase: Database): AmountPaidRepository {
        return AmountPaidRepositoryImp(dataBase.amountPaid())
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(dataBase: Database): CompanyRepository {
        return CompanyRepositoryImp(dataBase.companyDao())
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(dataBase: Database): CustomerRepository {
        return CustomerRepositoryImp(dataBase.customerDao())
    }
    @Provides
    @Singleton
    fun provideFarmerRepository(dataBase:Database): FarmerRepository {
        return FarmerRepositoryImp(dataBase.farmerDao())
    }
    @Provides
    @Singleton
    fun provideProductRepository(dataBase:Database): ProductRepository {
        return ProductRepositoryImp(dataBase.productDao())
    }
    @Provides
    @Singleton
    fun provideSalesProductRepository(dataBase:Database): SaleProductRepository {
        return SaleProductRepositoryImp(dataBase.salesProductDao())
    }
}