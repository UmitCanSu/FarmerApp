package com.example.farmerapp.data.repository

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.CustomerRepository
import com.example.farmerapp.domain.repository.dao.CustomerDao
import javax.inject.Inject

class CustomerRepositoryImp
@Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override suspend fun insertCustomer(customer: Customer) {
        return customerDao.insertCustomer(customer)
    }

    override suspend fun updateCustomer(customer: Customer) {
        return customerDao.updateCustomer(customer)
    }

    override suspend fun selectCustomerWithCustomerID(customerId: Int): Customer {
        return customerDao.selectCustomerWithCustomerID(customerId)
    }
}