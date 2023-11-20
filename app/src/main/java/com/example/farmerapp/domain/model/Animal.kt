package com.example.farmerapp.domain.model

import com.example.farmerapp.until.enums.AnimalType
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.enums.Gender
import java.time.LocalDateTime
import javax.inject.Inject

data class Animal
@Inject constructor(
    val id: Int,
    val company: Company,
    val addFarmer: Farmer,
    val animalNumber: String,
    val name: String,
    val years: String,
    val gender: Gender,
    val birthdate: LocalDateTime?,
    val animalType: AnimalType,
    val motherType: String?,
    val fatherType: String?,
) {
    constructor(
        animalNumber: String,
        name: String,
        years: String,
        gender: Gender,
        birthdate: LocalDateTime?,
        animalType: AnimalType,
        motherType: String?,
        fatherType: String?,
    ) : this(
        0,
        Sesion.getInstance().company!!,
        Sesion.getInstance().farmer!!,
        animalNumber, name, years, gender, birthdate, animalType, motherType, fatherType
    )


    constructor(
        id: Int,
        animalNumber: String,
        name: String,
        years: String,
        gender: Gender,
        birthdate: LocalDateTime?,
        animalType: AnimalType,
        motherType: String?,
        fatherType: String?,
    ) : this(
        id,
        Sesion.getInstance().company!!,
        Sesion.getInstance().farmer!!,
        animalNumber, name, years, gender, birthdate, animalType, motherType, fatherType
    )
}
