package com.example.farmerapp.presentation.sales_detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentSaleDetailBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.presentation.sales_detail_fragment.adapter.AmountPaidListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SaleDetailFragment : Fragment() {
    private lateinit var binding: FragmentSaleDetailBinding
    private val viewModel: SaleDetailViewModel by viewModels()
    private val args: SaleDetailFragmentArgs by navArgs()

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleDetailBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        viewModel.onEvent(SaleDetailOnEvent.SelectProduct(args.salesProductId,args.saleApiId))
        binding.addAmountPaid.setOnClickListener {
            val action =
                SaleDetailFragmentDirections.actionSaleDetailFragmentToAddAmountPaidFragment(args.saleApiId,args.salesProductId)
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
                    customDialog.errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }
                    ) {

                    }
                }

                is SaleDetailState.ShowAmountPaidList -> {
                    val adapter = AmountPaidListAdapter(it.amountPaidList)
                    binding.amountPaidRv.adapter = adapter
                    binding.amountPaidRv.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                }

                is SaleDetailState.ShowSalesProduct -> {
                    val salesProduct = it.salesProduct
                    binding.customerInfoText.text =
                        salesProduct.customer!!.name + " " + salesProduct.customer!!.sourName
                    binding.farmerInfoText.text =
                        salesProduct.farmer.name + " " + salesProduct.farmer.sourName
                    binding.productInfoText.text = salesProduct.product.name
                    binding.priceText.text = salesProduct.price.toString()
                }
            }
        }
    }

}