package com.example.farmerapp.domain.use_case.animal

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.repository.AnimalRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AnimalExtensions.toAnimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectedAnimalWithCompanyIdUseCase
@Inject
constructor(
    private val animalRepository: AnimalRepository
) {
    fun selectedAnimalWithCompanyIdUseCase(companyId: Int) = flow<Resource<List<Animal>>> {
        emit(Resource.Loading())
        val animalList = animalRepository.selectAnimalsWithCompanyId(companyId).map {
            it.toAnimal()
        }
        emit(Resource.Success(animalList))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}