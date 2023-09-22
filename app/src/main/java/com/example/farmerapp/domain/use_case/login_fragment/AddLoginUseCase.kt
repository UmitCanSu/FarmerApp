package com.example.farmerapp.domain.use_case.login_fragment

import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.repository.local.LoginRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.LoginExtensions.toLoginLocalDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddLoginToLocalUseCase
@Inject
constructor(
    private val loginRepository: LoginRepository
) {
    fun addLogin(login: Login, farmerId: Int) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val index = loginRepository.addLogin(login.toLoginLocalDto(farmerId))
        emit(Resource.Success(index > 0))

    }.catch { emit(Resource.Error(it.message!!)) }
}