package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.remote.dto.AnimalApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalApiRepository {
    suspend fun addAnimal(animalJson:String): AnimalApiDto
    suspend fun getAnimalListByCompanyId(companyId:String):List<AnimalApiDto>
}