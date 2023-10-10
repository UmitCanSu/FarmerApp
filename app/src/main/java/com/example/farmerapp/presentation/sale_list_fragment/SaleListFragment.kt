package com.example.farmerapp.presentation.sale_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.databinding.FragmentSaleListBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.presentation.sale_list_fragment.adapter.SaleListAdapter
import com.example.farmerapp.until.Sesion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SaleListFragment : Fragment() {
    private val viewModel: SaleListViewModel by viewModels()
    private lateinit var binding: FragmentSaleListBinding

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var companyId = Sesion.getInstance().company!!.id
        viewModel.onEvent(SaleListOnEvent.SaleList(companyId))
        binding.isPaidCheckbox.setOnCheckedChangeListener { _, isPaid ->
            viewModel.onEvent(SaleListOnEvent.IsPaid(isPaid))
        }
        lifecycleScope.launch { observableState() }
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is SaleListState.Idle -> {

                }

                is SaleListState.Loading -> {

                }

                is SaleListState.Error -> {
                    customDialog.errorDialogShow(it.errorMessage, {}) {}
                }

                is SaleListState.SaleList -> {
                    val adapter = SaleListAdapter(it.salesProductList)
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                }
            }
        }
    }
}