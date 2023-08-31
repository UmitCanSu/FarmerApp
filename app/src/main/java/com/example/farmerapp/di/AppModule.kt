package com.example.farmerapp.di

import android.content.Context
import com.example.farmerapp.data.local.AppDatabase
import com.example.farmerapp.data.remote.CompanyApiRepositoryImp
import com.example.farmerapp.data.remote.repository.FarmerApiRepositoryImp
import com.example.farmerapp.domain.repository.api.repository.CompanyApiRepository
import com.example.farmerapp.domain.repository.api.CompanyAppApi
import com.example.farmerapp.data.local.repository.AmountPaidRepositoryImp
import com.example.farmerapp.data.local.repository.AnimalRepositoryImp
import com.example.farmerapp.data.local.repository.CompanyRepositoryImp
import com.example.farmerapp.data.local.repository.CustomerRepositoryImp
import com.example.farmerapp.data.local.repository.FarmerRepositoryImp
import com.example.farmerapp.data.local.repository.ProductRepositoryImp
import com.example.farmerapp.data.local.repository.SaleProductRepositoryImp
import com.example.farmerapp.data.remote.repository.CustomerApiRepositoryImp
import com.example.farmerapp.data.remote.repository.ProductApiRepositoryImp
import com.example.farmerapp.domain.repository.api.CustomerApi
import com.example.farmerapp.domain.repository.api.repository.FarmerApiRepository
import com.example.farmerapp.domain.repository.api.FarmerAppApi
import com.example.farmerapp.domain.repository.api.ProductApi
import com.example.farmerapp.domain.repository.api.repository.CustomerApiRepository
import com.example.farmerapp.domain.repository.api.repository.ProductApiRepository
import com.example.farmerapp.domain.repository.room.AmountPaidRepository
import com.example.farmerapp.domain.repository.room.AnimalRepository
import com.example.farmerapp.domain.repository.room.CompanyRepository
import com.example.farmerapp.domain.repository.room.CustomerRepository
import com.example.farmerapp.domain.repository.room.FarmerRepository
import com.example.farmerapp.domain.repository.room.ProductRepository
import com.example.farmerapp.domain.repository.room.SaleProductRepository
import com.example.farmerapp.until.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideFarmerRepository(dataBase: AppDatabase): FarmerRepository {
        return FarmerRepositoryImp(dataBase.farmerDao())
    }

    @Provides
    @Singleton
    fun provideProductRepository(dataBase: AppDatabase): ProductRepository {
        return ProductRepositoryImp(dataBase.productDao())
    }

    @Provides
    @Singleton
    fun provideSalesProductRepository(dataBase: AppDatabase): SaleProductRepository {
        return SaleProductRepositoryImp(dataBase.salesProductDao())
    }

    @Provides
    @Singleton
    fun provideAnimalRepository(dataBase: AppDatabase): AnimalRepository {
        return AnimalRepositoryImp(dataBase.animalDao())
    }
    @Provides
    @Singleton
    fun provideCompanyApiRepository(companyAppApi: CompanyAppApi): CompanyApiRepository {
        return CompanyApiRepositoryImp(companyAppApi)
    }
    @Provides
    @Singleton
    fun provideFarmerApiRepository(farmerAppApi: FarmerAppApi): FarmerApiRepository {
        return FarmerApiRepositoryImp(farmerAppApi)
    }
    @Provides
    @Singleton
    fun provideProductApiRepository(productApi: ProductApi): ProductApiRepository {
        return ProductApiRepositoryImp(productApi)
    }
    @Provides
    @Singleton
    fun provideCustomerApiRepository(customerApi: CustomerApi): CustomerApiRepository {
        return CustomerApiRepositoryImp(customerApi)
    }
    @Provides
    @Singleton
    fun provideFarmerApi(): FarmerAppApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FarmerAppApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCompanyApi(): CompanyAppApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CompanyAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCustomerApi(): CustomerApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerApi::class.java)
    }

}