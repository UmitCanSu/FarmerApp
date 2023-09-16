package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.AnimalApiDto

interface AnimalApiRepository {
    suspend fun addAnimal(animalJson:String): AnimalApiDto
    suspend fun getAnimalListByCompanyId(companyId:String):List<AnimalApiDto>
}