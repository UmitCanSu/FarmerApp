package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.dto.AnimalApiDto
import com.example.farmerapp.data.remote.api.AnimalApi
import com.example.farmerapp.domain.repository.remote.AnimalApiRepository
import javax.inject.Inject

class AnimalApiRepositoryImp
@Inject constructor(
    private val animalApi: AnimalApi
) : AnimalApiRepository {
    override suspend fun addAnimal(animalJson: String): AnimalApiDto {
        return animalApi.addAnimal(animalJson)
    }

    override suspend fun getAnimalListByCompanyId(companyId: String): List<AnimalApiDto> {
        return animalApi.getAnimalListByCompanyId(companyId)
    }
}