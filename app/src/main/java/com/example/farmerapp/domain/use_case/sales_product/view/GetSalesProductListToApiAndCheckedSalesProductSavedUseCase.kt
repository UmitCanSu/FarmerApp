package com.example.farmerapp.domain.use_case.sales_product.view

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.amaount_list.AddAmountPaidToApiUseCase
import com.example.farmerapp.domain.use_case.amaount_list.AmountListCheckAndSaveToLocal
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyApiIdUseCase
import com.example.farmerapp.domain.use_case.customer.local.GetCustomerByApiIdToLocalUseCase
import com.example.farmerapp.domain.use_case.farmer.SelectFarmerByFarmerApiIdToLocalUseCase
import com.example.farmerapp.domain.use_case.product.local.GetProductByApiIdToLocalUseCase
import com.example.farmerapp.domain.use_case.sales_product.GetSaleListToApiUseCase
import com.example.farmerapp.domain.use_case.sales_product.GetSalesProductByApiIdToLocalUseCase
import com.example.farmerapp.domain.use_case.sales_product.InsertSalesProductToLocalUseCase
import com.example.farmerapp.domain.use_case.sales_product.SelectSalesProductWithCompanyIdToLocalUseCase
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSalesProductListToApiAndCheckedSalesProductSavedUseCase
@Inject constructor(
    private val getSalesProductListByCompanyIdToApiUseCase: GetSaleListToApiUseCase,
    private val getSalesProductByApiIdToLocalUseCase: GetSalesProductByApiIdToLocalUseCase,
    private val insertSalesProductUseCase: InsertSalesProductToLocalUseCase,
    private val getSaleProductListByCompanyIdToLocalUseCase: SelectSalesProductWithCompanyIdToLocalUseCase,
    private val getProductByApiIdToLocalUseCase: GetProductByApiIdToLocalUseCase,
    private val getFarmerByFarmerApiIdToLocalUseCase: SelectFarmerByFarmerApiIdToLocalUseCase,
    private val getCompanyByApiIdToLocalUseCase: SelectCompanyByCompanyApiIdUseCase,
    private val getCustomerByApiIdToLocalUseCase: GetCustomerByApiIdToLocalUseCase,
    private val insertAmountPaidToApiUseCase: AddAmountPaidToApiUseCase,
    private val amountListCheckAndSaveToLocal: AmountListCheckAndSaveToLocal
) {
    fun getSalesProductListToApiAndCheckedSalesProductSaved(
        companyApiId: String,
        companyLocalId: Int
    ) =
        getSalesProductListByCompanyIdToApiUseCase.getSaleListToApi(companyApiId)
            .filter { it is Resource.Success }
            .map {
                it.data?.map { salesProduct ->
                    getSalesProductByApiIdToLocalUseCase.getSalesProductByApiIdToLocal(salesProduct.apiId)
                        .filterIsInstance<Resource.Success<SalesProduct>>()
                        .map { it.data }
                        .filter { it == null }
                        .map {
                            getProductByApiIdToLocalUseCase.getProductByApiIdToLocal(salesProduct.product.apiId)
                                .filterIsInstance<Resource.Success<Product>>()
                                .map { it.data }
                                .filterNotNull()
                                .map {
                                    salesProduct.product.id = it.id
                                }.collect()
                        }.map {
                            getFarmerByFarmerApiIdToLocalUseCase.selectFarmerByFarmerApiIdToLocal(
                                salesProduct.farmer.farmerApiId
                            )
                                .filterIsInstance<Resource.Success<Farmer>>()
                                .map { it.data }
                                .filterNotNull()
                                .map { salesProduct.farmer.id = it.id }
                                .collect()
                        }.map {
                            getCompanyByApiIdToLocalUseCase.selectCompanyByCompanyApiId(
                                salesProduct.company.apiId
                            )
                                .filterIsInstance<Resource.Success<Company>>()
                                .map { it.data }
                                .filterNotNull()
                                .map { salesProduct.company.id = it.id }
                                .collect()
                        }.map {
                            getCustomerByApiIdToLocalUseCase.getCustomerByApiId(salesProduct.customer.apiId)
                                .filterIsInstance<Resource.Success<Customer>>()
                                .map { it.data }
                                .filterNotNull()
                                .map { salesProduct.customer.id = it.id }
                                .collect()
                        }.map {
                            insertSalesProductUseCase.insertSaleProduct(salesProduct)
                                .filterIsInstance<Resource.Success<SalesProduct>>()
                                .map { it.data }
                                .filterNotNull()
                                .map { salesProduct.id = it.id }
                                .collect()
                        }.map {
                            salesProduct.amountPaint.map {
                                it.salesProduct = salesProduct
                                amountListCheckAndSaveToLocal.insertAmountList(it)
                                    .collect()
                            }
                        }.collect()
                }
            }.flatMapMerge {
                getSaleProductListByCompanyIdToLocalUseCase.selectSalesProductWithCompanyId(
                    companyLocalId
                )
            }



}