package com.example.farmerapp.di

import android.content.Context
import com.example.farmerapp.data.local.AppDatabase
import com.example.farmerapp.data.local.repository.AmountPaidRepositoryImp
import com.example.farmerapp.data.local.repository.AnimalRepositoryImp
import com.example.farmerapp.data.local.repository.CompanyRepositoryImp
import com.example.farmerapp.data.local.repository.CustomerRepositoryImp
import com.example.farmerapp.data.local.repository.FarmerRepositoryImp
import com.example.farmerapp.data.local.repository.LoginRepositoryImp
import com.example.farmerapp.data.local.repository.ProductRepositoryImp
import com.example.farmerapp.data.local.repository.SaleProductRepositoryImp
import com.example.farmerapp.data.remote.repository.CompanyApiRepositoryImp
import com.example.farmerapp.data.remote.repository.AnimalApiRepositoryImp
import com.example.farmerapp.data.remote.repository.CustomerApiRepositoryImp
import com.example.farmerapp.data.remote.repository.FarmerApiRepositoryImp
import com.example.farmerapp.data.remote.repository.ProductApiRepositoryImp
import com.example.farmerapp.data.remote.repository.SaleApiRepositoryImp
import com.example.farmerapp.data.remote.api.AnimalApi
import com.example.farmerapp.data.remote.api.CompanyAppApi
import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.data.remote.api.FarmerAppApi
import com.example.farmerapp.data.remote.api.ProductApi
import com.example.farmerapp.data.remote.api.SaleApi
import com.example.farmerapp.domain.repository.remote.AnimalApiRepository
import com.example.farmerapp.domain.repository.remote.CompanyApiRepository
import com.example.farmerapp.domain.repository.remote.CustomerApiRepository
import com.example.farmerapp.domain.repository.remote.FarmerApiRepository
import com.example.farmerapp.domain.repository.remote.ProductApiRepository
import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.domain.repository.local.AmountPaidRepository
import com.example.farmerapp.domain.repository.local.AnimalRepository
import com.example.farmerapp.domain.repository.local.CompanyRepository
import com.example.farmerapp.domain.repository.local.CustomerRepository
import com.example.farmerapp.domain.repository.local.FarmerRepository
import com.example.farmerapp.domain.repository.local.LoginRepository
import com.example.farmerapp.domain.repository.local.ProductRepository
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.domain.use_case.IsInternetUseCase
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
    fun provideLoginRepository(dataBase: AppDatabase): LoginRepository {
        return LoginRepositoryImp(dataBase.loginDao())
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
    fun provideAnimalApiRepository(animalApi: AnimalApi): AnimalApiRepository {
        return AnimalApiRepositoryImp(animalApi)
    }


    @Provides
    @Singleton
    fun provideSaleApiRepository(saleApi: SaleApi): SaleApiRepository {
        return SaleApiRepositoryImp(saleApi)
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

    @Provides
    @Singleton
    fun provideAnimalApi(): AnimalApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSaleApi(): SaleApi {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SaleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideIsInternetUseCase(@ApplicationContext applicationContext: Context):IsInternetUseCase{
        return IsInternetUseCase(applicationContext)
    }

}