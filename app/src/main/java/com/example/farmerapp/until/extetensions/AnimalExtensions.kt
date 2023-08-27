package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.relations.AnimalRelation
import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.until.enums.AnimalType
import com.example.farmerapp.until.enums.Gender
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer

object AnimalExtensions {
    fun Animal.toAnimalDto(): AnimalDto {
        return AnimalDto(
            company.id,
            addFarmer.id,
            animalNumber,
            name,
            years,
            birthdate,
            animalType.ordinal,
            motherType,
            fatherType,
            gender.ordinal
        )
    }

    fun AnimalRelation.toAnimal(): Animal {
        return Animal(
            animalDto.id,
            companyDto.toCompany()!!,
            farmerDto.toFarmer(companyDto.toCompany()!!),
            animalDto.animalNumber,
            animalDto.name,
            animalDto.years,
            Gender.values()[animalDto.genderIndex],
            animalDto.birthdate,
            AnimalType.values()[animalDto.animalTypeIndex],
            animalDto.motherType,
            animalDto.fatherType,
        )

    }
}