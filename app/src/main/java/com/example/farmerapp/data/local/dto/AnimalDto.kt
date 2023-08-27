package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmerapp.until.enums.Gender
import java.time.LocalDateTime

@Entity("Animal")
data class AnimalDto(
    val companyId: Int,
    val addFarmerId: Int,
    val animalNumber: String,
    val name: String,
    val years: String,
    var birthdate: LocalDateTime?,
    val animalTypeIndex: Int,
    val motherType: String?,
    val fatherType: String?,
    val genderIndex: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
