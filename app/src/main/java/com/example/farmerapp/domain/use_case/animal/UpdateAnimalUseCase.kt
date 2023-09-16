package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.local.AnimalRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimalDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateAnimalUseCase @Inject constructor(
    private val animalRepository: AnimalRepository
) {
    fun updateAnimalUseCase(animal: Animal) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updatedCount = animalRepository.updateAnimal(animal.toAnimalDto())
        emit(Resource.Success(updatedCount > 0))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}