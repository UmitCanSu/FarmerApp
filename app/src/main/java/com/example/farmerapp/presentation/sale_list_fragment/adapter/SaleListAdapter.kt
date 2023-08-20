package com.example.farmerapp.presentation.sale_list_fragment.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.databinding.AdapterSalesProductListBinding
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.presentation.sale_list_fragment.SaleListFragmentDirections
import javax.inject.Inject

class SaleListAdapter
@Inject constructor(
    private val salesProductList: List<SalesProduct>
) : RecyclerView.Adapter<SaleListAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterSalesProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterSalesProductListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(salesProductList[position]) {
                binding.salesProductCard.strokeColor = if (isPaid) Color.RED else Color.GREEN
                binding.customerText.text = customerDataToString(customer)
                binding.productText.text = productDataToString(product)
            }
            binding.salesProductCard.setOnClickListener {
                val salesProductId = salesProductList[position].id
                val action = SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                    salesProductId
                )
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    private fun customerDataToString(customer: Customer): String {
        return customer.name + " " + customer.surName + " " + customer.phone
    }

    private fun productDataToString(product: Product): String {
        return product.name + " " + product.price + " " + product.unitType + " Id: " + product.id
    }

    override fun getItemCount(): Int = salesProductList.size
}


