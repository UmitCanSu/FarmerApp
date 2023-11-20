package com.example.farmerapp.domain.use_case.main

import com.example.farmerapp.domain.repository.local.AmountPaidRepository
import com.example.farmerapp.domain.repository.local.ProductRepository
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.domain.repository.remote.ProductApiRepository
import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.domain.use_case.customer.remote.GetCustomerListToApiAndSavedCustomerListUseCase
import com.example.farmerapp.domain.use_case.product.remote.GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase
import com.example.farmerapp.domain.use_case.sales_product.view.GetSalesProductListToApiAndCheckedSalesProductSavedUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaid
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductApiDto
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductDto
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleApiDto
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProduct
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProductDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class EqualizeLocalDbToApiUseCase
@Inject constructor(
    private val productRepository: ProductRepository,
    private val productApiRepository: ProductApiRepository,
    //
    private val saleProductRepository: SaleProductRepository,
    private val saleApiRepository: SaleApiRepository,
    private val amountPaidRepository: AmountPaidRepository,
    //
    private val getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase: GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase,
    private val getCustomerListToApiAndSavedCustomerListUseCase: GetCustomerListToApiAndSavedCustomerListUseCase,
    private val getSalesProductListToApiAndCheckedSalesProductSavedUseCase: GetSalesProductListToApiAndCheckedSalesProductSavedUseCase
) {
    fun equalizeLocalDbToApi(companyApiId: String, companyLocalId: Int) = flow<Resource<Boolean>> {
        merge(
            equalizeProductDbToApi(),
            getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase.getProductList(
                companyApiId,
                companyLocalId
            ),
            getCustomerListToApiAndSavedCustomerListUseCase.getCustomer(
                companyApiId,
                companyLocalId
            ),
            equalizeSalesProductDbToApi(),
            getSalesProductListToApiAndCheckedSalesProductSavedUseCase.getSalesProductListToApiAndCheckedSalesProductSaved(
                companyApiId,
                companyLocalId
            )
        ).collect()

    }.catch { emit(Resource.Error(it.message!!)) }


    private fun equalizeProductDbToApi() = flow<Boolean> {
        productRepository.selectFarmerEmptyApiIdProduct()
            .map {
                it.toProduct()
            }.map { product ->
                val json = Gson().toJson(product.toProductApiDto())
                val apiProduct = productApiRepository.addProduct(json).toProduct()
                product.apiId = apiProduct.apiId
                product
            }.map {
                val productDto = it.toProductDto()
                productDto.id = it.id
                productRepository.updateProduct(productDto)
            }.map { emit(it > 0) }


    }

    private fun equalizeSalesProductDbToApi() = flow<Boolean> {
        saleProductRepository.getSalesProductEmptyApiIdProduct()
            .map {
                it.toSaleProduct()
            }.map {
                val amountPaids =
                    amountPaidRepository.selectAmountPaidWithSalesProductId(it.id)
                        .map { it.toAmountPaid() }
                it.amountPaint = amountPaids
                it
            }.map {
                val salesJson = Gson().toJson(it.toSaleApiDto())
                val sale = saleApiRepository.addSale(salesJson).toSalesProduct()
                it.apiId = sale.apiId
                it
            }.map {
                saleProductRepository.updateSaleProduct(it.toSalesProductDto())
            }.map {
                emit(it > 0)
            }
    }
}