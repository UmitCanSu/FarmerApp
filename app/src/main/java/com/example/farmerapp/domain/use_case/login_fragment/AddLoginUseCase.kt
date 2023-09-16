package com.example.farmerapp.domain.use_case.login_fragment

import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.repository.local.LoginRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddLoginUseCase
@Inject
constructor(
    private val loginRepository: LoginRepository
) {
    fun addLogin(login: Login) = flow<Resource<Boolean>> {
        emit(Resource.Loading())


    }.catch { emit(Resource.Error(it.message!!)) }
}