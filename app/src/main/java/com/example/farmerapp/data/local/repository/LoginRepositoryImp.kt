package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.LoginLocalDto
import com.example.farmerapp.domain.repository.dao.LoginDao
import com.example.farmerapp.domain.repository.local.LoginRepository

class LoginRepositoryImp(
    private val loginDao: LoginDao
):LoginRepository {
    override suspend fun addLogin(loginLocalDto: LoginLocalDto): Long {
       return loginDao.addLogin(loginLocalDto)
    }

    override suspend fun getLogin(nickName: String, passwordHash: String):LoginLocalDto {
         return loginDao.getLogin(nickName,passwordHash)
    }
}