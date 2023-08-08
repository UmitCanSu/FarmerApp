package com.example.farmerapp.di

import android.content.Context
import androidx.room.Room
import com.example.farmerapp.data.local.AppDatabase
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
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabase.getInstance(applicationContext)
    }

    @Provides
    @Singleton
    fun provideAmountPaidRepository(dataBase: AppDatabase): AmountPaidRepository {
        return AmountPaidRepositoryImp(dataBase.amountPaid())
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(dataBase: AppDatabase): CompanyRepository {
        return CompanyRepositoryImp(dataBase.companyDao())
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(dataBase: AppDatabase): CustomerRepository {
        return CustomerRepositoryImp(dataBase.customerDao())
    }
    @Provides
    @Singleton
    fun provideFarmerRepository(dataBase:AppDatabase): FarmerRepository {
        return FarmerRepositoryImp(dataBase.farmerDao())
    }
    @Provides
    @Singleton
    fun provideProductRepository(dataBase:AppDatabase): ProductRepository {
        return ProductRepositoryImp(dataBase.productDao())
    }

    @Provides
    @Singleton
    fun provideSalesProductRepository(dataBase:AppDatabase): SaleProductRepository {
        return SaleProductRepositoryImp(dataBase.salesProductDao())
    }
}