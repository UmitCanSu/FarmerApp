package com.example.farmerapp.domain.use_case.login_fragment

import com.example.farmerapp.data.remote.dto.LoginApiDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.company.InsertCompanyToLocalUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyApiIdUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.domain.use_case.farmer.LoginToApiFarmerUseCase
import com.example.farmerapp.domain.use_case.farmer.SelectFarmerByFarmerApiIdToLocalUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.LoginExtensions.toLogin
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLoginToApiAndSaveCompanyAndFarmerAndLoginUseCase
@Inject constructor(
    private val getLoginToApiFarmerUseCase: LoginToApiFarmerUseCase,
    private val checkCompanyToLocalUseCase: SelectCompanyByCompanyApiIdUseCase,
    private val insertCompanyToLocalUseCase: InsertCompanyToLocalUseCase,
    private val insertFarmerToLocalUseCase: InsertFarmerToLocalUseCase,
    private val insertLoginToLocalUseCase: AddLoginToLocalUseCase,
    private val selectFarmerByFarmerApiIdUseCase: SelectFarmerByFarmerApiIdToLocalUseCase
) {
    fun getLoginToApiAndSaveCompanyAndFarmerAndLogin(loginApiDto: LoginApiDto) =
        flow<Resource<Farmer>> {
            emit(Resource.Loading())
            getLoginToApiFarmerUseCase.login(loginApiDto).collect { farmerResource ->
                var farmer = farmerResource.data!!
                checkCompanyToLocalUseCase.selectCompanyByCompanyApiId(farmer.company!!.apiId)
                    .collectLatest { companyResource ->
                        val company = companyResource.data!!
                        if (company == null) {
                            insertCompanyToLocalUseCase.insertCompany(company).collectLatest {
                                val savedLocalCompany = it.data
                                farmer.company = savedLocalCompany
                                insertFarmerToLocalUseCase.insertFarmer(farmer).collectLatest {
                                    farmer.id = it.data!!.toInt()
                                    insertLoginToLocalUseCase.addLogin(
                                        loginApiDto.toLogin(),
                                        farmer.id
                                    )
                                        .collectLatest {
                                            Resource.Success(farmer)
                                        }
                                }

                            }
                        } else {
                            // Bu kod ile farmırın local idsi kullanıyoruz. Veri kaydederken idye ihtiyacımız var
                            selectFarmerByFarmerApiIdUseCase.selectFarmerByFarmerApiIdToLocal(farmer.farmerApiId)
                                .collectLatest { localFarmerResources ->
                                    farmer = localFarmerResources.data!!
                                    emit(Resource.Success(farmer))
                                }
                        }
                    }
            }
        }.catch { emit(Resource.Error(it.message!!)) }

    fun getLoginToApiAndSaveCompanyAndFarmerAndLogin2(loginApiDto: LoginApiDto) =
        channelFlow<Resource<Farmer>> {
            getLoginToApiFarmerUseCase.login(loginApiDto).collectLatest { farmerResource ->
                if (farmerResource is Resource.Success)
                    if (farmerResource.data != null) {
                        var farmer = farmerResource.data!!
                        checkCompanyToLocalUseCase.selectCompanyByCompanyApiId(farmer.company!!.apiId)
                            .collectLatest { companyResource ->
                                if (companyResource is Resource.Success) {
                                    val company = companyResource.data
                                    if (company == null) {
                                        insertCompanyToLocalUseCase.insertCompany(farmer.company!!)
                                            .collectLatest {
                                                if (it is Resource.Success) {
                                                    val savedLocalCompany = it.data
                                                    farmer.company = savedLocalCompany
                                                    insertFarmerToLocalUseCase.insertFarmer(farmer)
                                                        .collectLatest {
                                                            if (it is Resource.Success) {
                                                                farmer.id = it.data!!.toInt()
                                                                insertLoginToLocalUseCase.addLogin(
                                                                    loginApiDto.toLogin(),
                                                                    farmer.id
                                                                )
                                                                    .collectLatest {
                                                                        if (it is Resource.Success)
                                                                            send(Resource.Success(farmer))
                                                                    }
                                                            }
                                                        }
                                                }
                                            }
                                    } else {
                                        // Bu kod ile farmırın local idsi kullanıyoruz. Veri kaydederken idye ihtiyacımız var
                                        selectFarmerByFarmerApiIdUseCase.selectFarmerByFarmerApiIdToLocal(
                                            farmer.farmerApiId
                                        )
                                            .collectLatest { localFarmerResources ->
                                                if (localFarmerResources is Resource.Success) {
                                                    farmer = localFarmerResources.data!!
                                                    send(Resource.Success(farmer))
                                                }
                                            }
                                    }
                                }
                            }
                    }
            }
        }.catch { emit(Resource.Error(it.message!!)) }
}