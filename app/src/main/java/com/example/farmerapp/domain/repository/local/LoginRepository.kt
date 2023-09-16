package com.example.farmerapp.domain.repository.local


import com.example.farmerapp.data.local.dto.LoginLocalDto

interface LoginRepository {
    suspend fun addLogin(loginLocalDto: LoginLocalDto):Long
    suspend fun getLogin(nickName: String, passwordHash: String):LoginLocalDto
}