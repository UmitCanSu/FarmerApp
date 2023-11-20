package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.use_case.customer.local.GetCustomerByApiIdToLocalUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmountListCheckAndSaveToLocal
@Inject constructor(
    private val selectCustomerByApiIdToLocalUseCase: GetCustomerByApiIdToLocalUseCase,
    private val insertAmountPaidUseCase: InsertAmountPaidUseCase,
) {

    fun insertAmountList(amountPaid: AmountPaid) =
        selectCustomerByApiIdToLocalUseCase.getCustomerByApiId(amountPaid.customer.apiId)
            .filterIsInstance<Resource.Success<Customer>>()
            .map { it.data }
            .filterNotNull()
            .map {
                amountPaid.customer.id = it.id
            }.map {
                insertAmountPaidUseCase.insertAmountPaid(amountPaid).collect()
            }
}