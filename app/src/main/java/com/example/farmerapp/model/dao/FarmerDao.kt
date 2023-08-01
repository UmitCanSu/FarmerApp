package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.model.Farmer

@Dao
interface FarmerDao {
    @Insert
    fun insertFarmer(farmer: Farmer)

    @Update
    fun updateFarmer(farmer: Farmer)

    @Delete
    fun deleteFarmer(farmer: Farmer)

    @Query("Select * from Farmer where id=:farmerId")
    fun selectFarmerWithId(farmerId: Int)

    @Query("Select * from  Farmer where id=:companyID ")
    fun selectFarmersWithCompanyId(companyID: Int): List<Farmer>
}