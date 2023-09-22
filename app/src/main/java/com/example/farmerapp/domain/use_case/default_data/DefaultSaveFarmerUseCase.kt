package com.example.farmerapp.domain.use_case.default_data

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyIdUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultSaveFarmerUseCase
@Inject constructor(
    private val insertFarmer: InsertFarmerToLocalUseCase,
    private val selectCompanyWithCompanyIdUseCase: SelectCompanyByCompanyIdUseCase
) {
    fun defaultSaveFarmer() = flow<Resource<Boolean>> {
        selectCompanyWithCompanyIdUseCase.selectCompanyByCompanyId(1).onEach {
            if (it.data != null) {
                val company = it.data!!
                val farmer = Farmer(company, "Farmer", "- 1", 18, FarmerStatus.Farmer)
                insertFarmer.insertFarmer(farmer).onEach {
                    emit(Resource.Success(it.data!!>0))
                }
            } else {
                emit(Resource.Error("Company can not find"))
            }
        }
    }.catch { emit(Resource.Error(it.message!!)) }
}