package com.example.farmerapp.until.extetensions

import android.util.Log
import com.example.farmerapp.data.local.dto.LoginLocalDto
import com.example.farmerapp.data.remote.dto.LoginApiDto
import com.example.farmerapp.domain.model.Login

object LoginExtensions {
    fun LoginLocalDto.toLogin(): Login {
        return Login(
            nickName,
            passwordHash
        )
    }

    fun Login.toLoginLocalDto(farmerId: Int): LoginLocalDto {
        return LoginLocalDto(
            nickName,
            passwordHash,
            farmerId
        )
    }

    fun LoginApiDto.toLogin():Login{
        return Login(nickName,password)
    }
}