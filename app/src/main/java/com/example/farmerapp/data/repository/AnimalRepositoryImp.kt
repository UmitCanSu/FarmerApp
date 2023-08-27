package com.example.farmerapp.data.repository

import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.relations.AnimalRelation
import com.example.farmerapp.domain.repository.AnimalRepository
import com.example.farmerapp.domain.repository.dao.AnimalDao
import javax.inject.Inject

class AnimalRepositoryImp
@Inject
constructor(
    private val animalRepository: AnimalDao
) : AnimalRepository {
    override fun insertAnimal(animalDto: AnimalDto): Long {
        return animalRepository.insertAnimal(animalDto)
    }

    override fun updateAnimal(animalDto: AnimalDto): Int {
        return animalRepository.updateAnimal(animalDto)
    }

    override fun selectAnimalsWithCompanyId(companyId: Int): List<AnimalRelation> {
        return animalRepository.selectAnimalsWithCompanyId(companyId)
    }

    override fun selectAnimalWithAnimalId(animalId: Int): AnimalRelation {
        return animalRepository.selectAnimalWithAnimalId(animalId)
    }
}