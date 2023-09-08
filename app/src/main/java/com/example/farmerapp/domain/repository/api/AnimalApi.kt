package com.example.farmerapp.domain.repository.api

import com.example.farmerapp.data.remote.dto.AnimalApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalApi {
    @POST("Animal/AddAnimal")
    suspend fun addAnimal(
        @Query("animalJson") animalJson:String
    ):AnimalApiDto

    @POST("Animal/GetAnimalByCompanyId")
    suspend fun getAnimalListByCompanyId(
        @Query("companyId") companyId:String
    ):List<AnimalApiDto>
}