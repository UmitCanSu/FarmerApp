package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.api.repository.AnimalApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimal
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimalListToApiUseCase
@Inject constructor(
    private val animalApiRepository: AnimalApiRepository
) {
    fun getAnimals(companyId: String) = flow<Resource<List<Animal>>> {
        emit(Resource.Loading())
        val animalList = animalApiRepository.getAnimalListByCompanyId(companyId).map {
            it.toAnimal()
        }
        emit(Resource.Success(animalList))
    }.catch { emit(Resource.Error(it.message!!)) }
}