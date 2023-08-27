package com.example.farmerapp.presentation.animal_list_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.databinding.AdapterAnimalListBinding
import com.example.farmerapp.domain.model.Animal
import javax.inject.Inject

class AnimalListAdapter
@Inject constructor(
    private val animalList: List<Animal>
) : RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterAnimalListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            AdapterAnimalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with(animalList[position]) {
             binding.nameText.text = name
            binding.animalNumberText.text = animalNumber
            binding.birhDateText.text = birthdate.toString()
            binding.genderText.text = gender.name
        }
    }

    override fun getItemCount(): Int = animalList.size
}