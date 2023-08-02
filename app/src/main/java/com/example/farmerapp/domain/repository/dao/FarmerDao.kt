package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.dto.FarmerDto
import com.example.farmerapp.domain.model.Farmer

@Dao
interface FarmerDao {
    @Insert
    suspend fun insertFarmer(farmer: Farmer)

    @Update
    suspend fun updateFarmer(farmer: Farmer)

    @Delete
    suspend fun deleteFarmer(farmer: Farmer)

    @Query("Select * from Farmer where id=:farmerId")
    suspend fun selectFarmerWithId(farmerId: Int): FarmerDto

    @Query("Select * from  Farmer where id=:companyID ")
    suspend fun selectFarmersWithCompanyId(companyID: Int): List<FarmerDto>
}