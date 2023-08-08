package com.example.farmerapp.presentation.sale_fragment

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.databinding.FragmentSaleBinding
import com.example.farmerapp.domain.model.Product
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaleFragment : Fragment() {
    private lateinit var binding: FragmentSaleBinding
    private lateinit var viewModel: SaleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SaleViewModel::class.java]
        lifecycleScope.launch { observeSaleFragmentState() }
        // viewModel.onEvent(SaleFragmentOnEvent.CalculatePrice(0))
        binding.choseProduct.selectedItemPosition
        val spinnerListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.onEvent(SaleFragmentOnEvent.SelectProduct(p2))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }
        binding.choseProduct.onItemSelectedListener = spinnerListener

        val ob = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
        binding.numberText.addTextChangedListener {
            val salesProductCount: Int = it.toString().toInt()
            viewModel.onEvent(SaleFragmentOnEvent.CalculatePrice(salesProductCount))
        }
    }

    private suspend fun observeSaleFragmentState() {

        viewModel.state.collect { state ->
            when (state) {
                is SaleFragmentState.Idle -> {}
                is SaleFragmentState.Loading -> {}
                is SaleFragmentState.Error -> {}
                is SaleFragmentState.Calculate -> {
                    binding.totalPrice.text = "Total Price: " + state.amountPrice
                }

                is SaleFragmentState.ProdcutList -> {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item,
                        state.productList.map { it.name })
                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.choseProduct.adapter = adapter
                }

                is SaleFragmentState.SelectedProduct -> {
                    val product = state.selectedProduct
                    binding.productName.text = product.name
                    binding.productPrice.text = product.price.toString()
                }
            }
        }
    }
}