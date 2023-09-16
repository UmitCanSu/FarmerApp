package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.remote.AnimalApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimal
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimalApiDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddAnimalToApiUseCase
@Inject constructor(
    private val animalApiRepository: AnimalApiRepository
){
    fun addAnimal(animal: Animal)= flow<Resource<Animal>>{
        emit(Resource.Loading())
        val animalToAnimalApiDto = animal.toAnimalApiDto()
        val animalApiDtoToJson = Gson().toJson(animalToAnimalApiDto)
        val animal = animalApiRepository.addAnimal(animalApiDtoToJson).toAnimal()
        emit(Resource.Success(animal))
    }.catch { emit(Resource.Error(it.message!!)) }
}