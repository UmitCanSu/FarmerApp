package com.example.farmerapp.presentation.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.databinding.AdapterProductListBinding
import com.example.farmerapp.domain.model.Product
import javax.inject.Inject

class ProductListAdapter
@Inject constructor(
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterProductListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = AdapterProductListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(productList[position]) {
                binding.productText.text = productToString(this)
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    private fun productToString(product: Product): String {
        return product.name + " " + product.unitType + " " + product.price
    }

}