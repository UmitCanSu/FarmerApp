package com.example.farmerapp.presentation.sales_detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentSaleDetailBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.presentation.sales_detail_fragment.adapter.AmountPaidListAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaleDetailFragment : Fragment() {
    private lateinit var binding: FragmentSaleDetailBinding
    private lateinit var viewModel: SaleDetailViewModel
    private val args: SaleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[SaleDetailViewModel::class.java]
        binding = FragmentSaleDetailBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        viewModel.onEvent(SaleDetailOnEvent.SelectProduct(args.salesProductId))
        binding.addAmountPaid.setOnClickListener {
            val action =
                SaleDetailFragmentDirections.actionSaleDetailFragmentToAddAmountPaidFragment(args.salesProductId)
            Navigation.findNavController(it).navigate(action)
        }
    }


    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is SaleDetailState.Idle -> {

                }

                is SaleDetailState.Loading -> {

                }

                is SaleDetailState.Error -> {
                    CustomDialog(requireActivity()).errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }, onCancelClick = {

                        }
                    )
                }

                is SaleDetailState.ShowAmountPaidList -> {
                    val adapter = AmountPaidListAdapter(it.amountPaidList)
                    binding.amountPaidRv.adapter = adapter
                    binding.amountPaidRv.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                }

                is SaleDetailState.ShowSalesProduct -> {
                    val salesProduct = it.salesProduct
                    binding.customerInfoText.text = Gson().toJson(salesProduct.customer)
                    binding.farmerInfoText.text = Gson().toJson(salesProduct.farmer)
                    binding.productInfoText.text = Gson().toJson(salesProduct.product)
                }
            }
        }
    }

}