package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Farmer

@Dao
interface FarmerDao {
    @Insert
    suspend fun insertFarmer(farmer: FarmerDto):Long

    @Update
    suspend fun updateFarmer(farmer: FarmerDto):Int

    @Delete
    suspend fun deleteFarmer(farmer: FarmerDto):Int

    @Query("Select * from Farmer where id=:farmerId")
    suspend fun selectFarmerWithId(farmerId: Int): FarmerRelations

    @Query("Select * from  Farmer where id=:companyID ")
    suspend fun selectFarmersWithCompanyId(companyID: Int): List<FarmerRelations>
}