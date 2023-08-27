package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.relations.AnimalRelation

@Dao
interface AnimalDao {
    @Insert
    fun insertAnimal(animalDto: AnimalDto): Long

    @Update
    fun updateAnimal(animalDto: AnimalDto): Int

    @Query("SELECT * FROM Animal where companyId =:companyId")
    @Transaction
    fun selectAnimalsWithCompanyId(companyId: Int): List<AnimalRelation>

    @Query("SELECT * FROM Animal where id=:animalId")
    @Transaction
    fun selectAnimalWithAnimalId(animalId: Int): AnimalRelation
}