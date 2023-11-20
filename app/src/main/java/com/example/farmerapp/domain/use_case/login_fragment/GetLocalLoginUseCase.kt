package com.example.farmerapp.domain.use_case.login_fragment

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.repository.local.FarmerRepository
import com.example.farmerapp.domain.repository.local.LoginRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalLoginUseCase
@Inject constructor(
    private val loginRepository: LoginRepository,
    private val farmerRepository: FarmerRepository,
) {
    fun getLogin(login: Login) = flow<Resource<Farmer>> {
        emit(Resource.Loading())
        val loginLocalDto = loginRepository.getLogin(login.nickName, login.passwordHash)
        val farmer = farmerRepository.selectFarmerById(loginLocalDto.farmerId).toFarmer()
        emit(Resource.Success(farmer))
    }.catch { emit(Resource.Error(it.message!!)) }
}