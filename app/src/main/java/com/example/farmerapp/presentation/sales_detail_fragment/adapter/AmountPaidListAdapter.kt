package com.example.farmerapp.presentation.sales_detail_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.R
import com.example.farmerapp.databinding.AdapterAmountPaidListBinding
import com.example.farmerapp.domain.model.AmountPaid
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AmountPaidListAdapter
@Inject constructor(
    private val amountPaidList: List<AmountPaid>
) : RecyclerView.Adapter<AmountPaidListAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterAmountPaidListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AmountPaidListAdapter.ViewHolder {
        val binding =
            AdapterAmountPaidListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with(amountPaidList[position]) {
            binding.customerText.text = customer.name + " " + customer.surName
            binding.priceText.text = "${binding.root.context.getString(R.string.price)}:  $price"
            binding.dateText.text = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        }

    }

    override fun getItemCount(): Int = amountPaidList.size
}