package com.example.farmerapp.di

import android.app.Application
import androidx.room.Room
import com.example.farmerapp.until.Database
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
    fun provideAppDatabase(@ApplicationContext applicationContext: Application): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "farmer_db"
        ).build()
    }
}