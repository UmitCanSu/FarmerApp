package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.farmerapp.data.local.dto.LoginLocalDto

@Dao
interface LoginDao {
    @Insert
    suspend fun addLogin(loginLocalDto: LoginLocalDto): Long

    @Query("Select * from Login where  nickName = :nickName and passwordHash = :passwordHash")
    suspend fun getLogin(nickName: String, passwordHash: String):LoginLocalDto
}