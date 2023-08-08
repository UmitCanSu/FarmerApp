package com.example.farmerapp.domain.use_case.default_data

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.company.SelectCompanyWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerUseCase
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultSaveFarmerUseCase
@Inject constructor(
    private val insertFarmer: InsertFarmerUseCase,
    private val selectCompanyWithCompanyIdUseCase: SelectCompanyWithCompanyIdUseCase
) {
    fun defaultSaveFarmer() = flow<Resource<Boolean>> {
        selectCompanyWithCompanyIdUseCase.selectCompanyWithCompanyId(1).onEach {
            if (it.data != null) {
                val company = it.data!!
                val farmer = Farmer(company, "Farmer", "- 1", 18, FarmerStatus.Farmer)
                insertFarmer.insertFarmer(farmer).onEach {
                    emit(it)
                }
            } else {
                emit(Resource.Error("Company can not find"))
            }
        }
    }.catch { emit(Resource.Error(it.message!!)) }
}