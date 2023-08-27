package com.example.farmerapp.presentation.animal_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.databinding.FragmentAnimalListBinding
import com.example.farmerapp.presentation.add_animal_fragment.AddAnimalFragmentOnEvent
import com.example.farmerapp.presentation.dialog.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AnimalListFragment : Fragment() {
    private val viewModel: AnimalListViewModel by viewModels()
    private lateinit var binding: FragmentAnimalListBinding

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        viewModel.onEvent(AnimalListFragmentEvent.GetAnimalList)
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is AnimalListState.Idle -> {

                }

                is AnimalListState.Loading -> {

                }

                is AnimalListState.Success -> {
                    val adapter = AnimalListAdapter(it.animals)
                    binding.recyclerView.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                }

                is AnimalListState.Error -> {
                    // customDialog.errorDialogShow(it.errorMessage, {}, {})
                }
            }
        }
    }

}