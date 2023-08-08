package com.example.farmerapp.domain.use_case.default_data

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.use_case.company.InsertCompanyUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultSaveCompanyUseCase
@Inject constructor(
    private val insertCompantUseCase: InsertCompanyUseCase
) {
    fun defaultSaveCompany() = flow<Resource<Boolean>> {
        val company = Company("Deneme", "Artvin/Savsat/Armutlu mah.", "05340000000")
        insertCompantUseCase.insertCompany(company).onEach {
            emit(it)
        }.catch { emit(Resource.Error(it.message!!)) }
    }.catch { emit(Resource.Error(it.message!!)) }
}