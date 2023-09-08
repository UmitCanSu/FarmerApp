package com.example.farmerapp.presentation.add_animal_fragment

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentAddAnimalBinding
import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.Constant
import com.example.farmerapp.until.enums.AnimalType
import com.example.farmerapp.until.enums.Gender
import com.example.farmerapp.until.extetensions.Extensions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class AddAnimalFragment : Fragment() {

    private lateinit var binding: FragmentAddAnimalBinding
    private lateinit var viewModel: AddAnimalViewModel
    private val editTextLayoutList =
        ArrayList<com.google.android.material.textfield.TextInputLayout>()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAnimalBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddAnimalViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        fillEditTextLayout()
        setAnimalTypeSpinner()
        setTodayDateEditText()

        binding.saveButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextLayoutList)) {
                val animal = Animal(
                    binding.animalNumberLayout.editText!!.text.toString(),
                    binding.nameTextLayout.editText!!.text.toString(),
                    binding.birtDatePicker.year.toString(),
                    getGenderRadiaButton(),
                    setLocalDateTime(),
                    AnimalType.values()[binding.animalTypeSpinner.selectedItemPosition],
                    "",
                    ""
                )
                viewModel.onEvent(AddAnimalFragmentOnEvent.AddAnimal(animal))
            }
        }

        binding.dateTextLayout.setOnFocusChangeListener { view, b ->
            createDatePicker()
        }
        binding.dateEditText.setOnFocusChangeListener { _, isFocus ->
            if (isFocus) createDatePicker()
        }
    }

    private fun getGenderRadiaButton(): Gender {
        return when (binding.genderRadioGroup.checkedRadioButtonId) {
            binding.maleRadioButton.id -> Gender.Male
            binding.femaleRadioButton.id -> Gender.Female
            else -> {
                Gender.Male
            }
        }
    }

    private fun setTodayDateEditText() {
        val todayDateString = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        binding.dateEditText.setText(todayDateString)
    }

    private fun animalTypeList(): List<AnimalType> {
        val animalTypeList = ArrayList<AnimalType>()
        animalTypeList.add(AnimalType.Cow)
        animalTypeList.add(AnimalType.Chicken)
        animalTypeList.add(AnimalType.Sheep)
        animalTypeList.add(AnimalType.Goat)
        return animalTypeList
    }

    private fun setAnimalTypeSpinner() {

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            animalTypeList().map { it.name }
        )
        binding.animalTypeSpinner.adapter = adapter
    }

    private fun fillEditTextLayout() {
        editTextLayoutList.add(binding.nameTextLayout)
        editTextLayoutList.add(binding.dateTextLayout)
        editTextLayoutList.add(binding.animalNumberLayout)
    }

    private fun createDatePicker(): DatePickerDialog {
        val datePickerDialog = DatePickerDialog(requireContext())
        datePickerDialog.datePicker.maxDate =
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
            val localDateTime = setLocalDateTime(datePicker)
            val dateString = localDateTime.format(DateTimeFormatter.ISO_DATE)
            binding.dateTextLayout.editText!!.setText(dateString)
        }
        datePickerDialog.show()
        return datePickerDialog
    }

    private fun setLocalDateTime(): LocalDateTime {
        val year = binding.birtDatePicker.year
        val month = binding.birtDatePicker.month + 1// 0-based
        val day = binding.birtDatePicker.dayOfMonth
        return LocalDateTime.of(year, month, day, 0, 0)
    }

    private fun setLocalDateTime(datePicker: DatePicker): LocalDateTime {
        val year = datePicker.year
        val month = datePicker.month + 1// 0-based
        val day = datePicker.dayOfMonth
        return LocalDateTime.of(year, month, day, 0, 0)
    }


    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is AddAnimalFragmentState.Idle -> {

                }

                is AddAnimalFragmentState.Loading -> {

                }

                is AddAnimalFragmentState.Success -> {
                    /*
                    customDialog.successDialogShow(getString(R.string.success),Constant.SUCCESS_TIMER) {
                        Navigation.findNavController(requireView()).popBackStack()
                    }
                    */
                }

                is AddAnimalFragmentState.Error -> {
                    customDialog.errorDialogShow(getString(R.string.error),{}) {}
                }
            }
        }
    }

}