package com.example.farmerapp.presentation.sale_fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmerapp.databinding.FragmentSaleBinding
import kotlinx.coroutines.flow.onEach

class SaleFragment : Fragment() {
    private lateinit var binding: FragmentSaleBinding
    private lateinit var viewModel: SaleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SaleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun observeSaleFragmentState() {
        viewModel.state.onEach { state ->
            when (state) {
                is SaleFragmentState.Idle -> {}
                is SaleFragmentState.Loading -> {}
                is SaleFragmentState.Error -> {}
                is SaleFragmentState.Calculate -> {}
                is SaleFragmentState.ProdcutList -> {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item,
                        state.productList.map { it.name })
                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.choseProduct.adapter = adapter
                }


            }

        }
    }
}