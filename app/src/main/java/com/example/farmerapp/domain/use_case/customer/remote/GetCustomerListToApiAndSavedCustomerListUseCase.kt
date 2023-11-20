package com.example.farmerapp.domain.use_case.customer.remote

import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.local.CustomerRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomerDto
import com.example.farmerapp.until.extetensions.FarmerExtensions.toCustomer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCustomerListToApiAndSavedCustomerListUseCase
@Inject constructor(
    private val customerApi: CustomerApi,
    private val customerRepository: CustomerRepository,
) {
    fun getCustomer(companyApiId: String, companyLocalId: Int) =
        getCustomerListToAPi(companyApiId, companyLocalId)
            .filter { it is Resource.Success }
            .map {
                it.data!!.map {
                    val customer: Customer = it
                    getFindCustomer(customer)
                        .filter { it is Resource.Success }
                        .map {
                            if (it.data == false)
                                addCustomerToLocal(customer).collect()

                        }
                        .collect()
                }
            }.flatMapConcat {
                getCustomerListToLocal()
            }.catch { emit(Resource.Error(it.message!!)) }


    private fun getCustomerListToAPi(companyApiId: String, companyLocalId: Int) =
        flow<Resource<List<Customer>>> {
            val customerApiList = customerApi.getCustomerList(companyApiId).customers.map {
                it.toCustomer()
            }
            emit(Resource.Success(customerApiList))

        }.catch { emit(Resource.Error(it.message!!)) }

    private fun getFindCustomer(customer: Customer) = flow<Resource<Boolean>> {
        val customerDto = customerRepository.getCustomerByApiId(customer.apiId)
        emit(Resource.Success(customerDto != null))
    }.catch { emit(Resource.Error(it.message!!)) }

    private fun addCustomerToLocal(customer: Customer) = flow<Resource<Boolean>> {
        customerRepository.insertCustomer(customer.toCustomerDto())
        emit(Resource.Success(true))
    }.catch { emit(Resource.Error(it.message!!)) }

    private fun getCustomerListToLocal() = flow<Resource<List<Customer>>> {
        val customerList = customerRepository.getAllCustomer().map { it.toCustomer() }
        emit(Resource.Success(customerList))
    }.catch { emit(Resource.Error(it.message!!)) }
}