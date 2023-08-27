package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.AnimalRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimalDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class InsertAnimalUseCase
@Inject constructor(
    private val animalRepository: AnimalRepository
) {
    fun insertAnimalUseCase(animal: Animal) = flow<Resource<Long>> {
        emit(Resource.Loading())
        val id = animalRepository.insertAnimal(animal.toAnimalDto())
        emit(Resource.Success(id))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}