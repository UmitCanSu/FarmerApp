package com.example.farmerapp.domain.use_case.login_fragment

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.repository.local.LoginRepository
import com.example.farmerapp.domain.use_case.farmer.SelectFarmerByIdUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalLoginUseCase
@Inject constructor(
    private val loginRepository: LoginRepository,
    private val selectFarmerByIdUseCase: SelectFarmerByIdUseCase
) {
    fun getLogin(login: Login) = flow<Resource<Farmer>> {
        emit(Resource.Loading())
        val loginLocalDto = loginRepository.getLogin(login.nickName, login.passwordHash)
        if (loginLocalDto != null) {
            emit(selectFarmerByIdUseCase.selectFarmerWithId(loginLocalDto.farmerId).first())
        } else {
            emit(Resource.Error("Can not find Farmer"))
        }

    }.catch { emit(Resource.Error(it.message!!)) }
}