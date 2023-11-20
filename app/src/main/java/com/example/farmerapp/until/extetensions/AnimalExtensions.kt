package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.relations.AnimalRelation
import com.example.farmerapp.data.remote.dto.AnimalApiDto
import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.until.enums.AnimalType
import com.example.farmerapp.until.enums.Gender
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import java.time.LocalDateTime

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

    fun Animal.toAnimalApiDto(): AnimalApiDto {
        return AnimalApiDto(
            "",
            company.id.toString(),
            addFarmer.id.toString(),
            name,
            birthdate.toString(),
            null,
            null,
            gender.ordinal
        )
    }

    fun AnimalApiDto.toAnimal(): Animal {
        return Animal(
            "",
            name,
            "0",
            Gender.values()[genderIndex],
            LocalDateTime.now(),
            AnimalType.Cow,
            null,
            null
        )
    }
}