package com.example.farmerapp.domain.use_case.sales_list_fragment

import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class SalesListFilterUseCase
@Inject constructor(
    private val saleListRepository: SaleProductRepository
) {
    fun getSalesProductFilter(
        customerId: Int,
        productId: Int,
        isPaid: Boolean,
        startDate: LocalDate,
        endDate: LocalDate
    ) = flow<Resource<List<SalesProduct>>> {
        emit(Resource.Loading())
        val companyID = Sesion.getInstance().company!!.id
        val farmerId = Sesion.getInstance().farmer!!.id
        val salesProductList = saleListRepository.selectSalesProductFilter(
            companyID,
            customerId,
            farmerId,
            productId,
            isPaid,
            startDate,
            endDate
        )
            .map { it.toSaleProduct() }
        emit(Resource.Success(salesProductList))

    }.catch { emit(Resource.Error(it.message!!)) }
}