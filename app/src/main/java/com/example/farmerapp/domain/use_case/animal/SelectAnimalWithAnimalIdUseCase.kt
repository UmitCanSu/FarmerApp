package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.room.AnimalRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectAnimalWithAnimalIdUseCase
@Inject constructor(
    private val animalRepository: AnimalRepository
) {
    fun selectedAnimalWithAnimalIdUseCase(animalId: Int) = flow<Resource<Animal>> {
        emit(Resource.Loading())
        val animal = animalRepository.selectAnimalWithAnimalId(animalId).toAnimal()
        emit(Resource.Success(animal))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}