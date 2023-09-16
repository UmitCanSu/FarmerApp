package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class LoginLocalDto(
    var nickName:String,
    var passwordHash:String,
    @PrimaryKey(autoGenerate = false)
    var farmerId:Int
)
