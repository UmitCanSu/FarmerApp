package com.example.farmerapp.domain.repository.room

import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.relations.AnimalRelation

interface AnimalRepository {
    fun insertAnimal(animalDto: AnimalDto): Long
    fun updateAnimal(animalDto: AnimalDto): Int
    fun selectAnimalsWithCompanyId(companyId: Int): List<AnimalRelation>
    fun selectAnimalWithAnimalId(animalId: Int): AnimalRelation
}