package com.example.farmerapp.presentation.dialog


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.example.farmerapp.R
import com.example.farmerapp.databinding.DialogCustemBinding
import com.example.farmerapp.until.DialogType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CustomDialog
@Inject constructor(
    @ApplicationContext private val context: Context,
) : AlertDialog(context) {
    private var binding: DialogCustemBinding =
        DialogCustemBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun successDialogShow(message: String, timer: Int, onConfirmClick: () -> Unit) {
        binding.image.setImageDrawable(context.getDrawable(R.drawable.baseline_success_150))
        binding.errorMessageText.text = message
        binding.noButton.visibility = View.INVISIBLE
        binding.successButton.setOnClickListener {
            onConfirmClick.invoke()
            dismiss()
        }
        show()
        setTimer(timer, onConfirmClick)
    }

    private fun setTimer(timer: Int, onConfirmClick: () -> Unit) {
        val timer: Long = (timer * 1000).toLong()
        Handler(Looper.getMainLooper()).postDelayed({
            onConfirmClick()
            dismiss()
        }, timer)

    }

    fun successDialogShow(message: String, onConfirmClick: () -> Unit) {
        successDialogShow(message, 0, onConfirmClick)
    }

     fun errorDialogShow(
        message: String,
        onConfirmClick: () -> Unit,
        onCancelClick: () -> Unit?
    ) {
        binding.image.setImageDrawable(context.getDrawable(R.drawable.baseline_error_150))
        binding.errorMessageText.text = message
        binding.successButton.setOnClickListener {
            onConfirmClick.invoke()
            dismiss()
        }
        binding.noButton.setOnClickListener {
            onCancelClick.invoke()
            dismiss()
        }
    }

     fun warningDialogShow(
        message: String,
        onConfirmClick: () -> Unit,
        onCancelClick: () -> Unit?
    ) {
        binding.image.setImageDrawable(context.getDrawable(R.drawable.baseline_warning_150))
        binding.errorMessageText.text = message
        binding.successButton.setOnClickListener {
            onConfirmClick.invoke()
            dismiss()
        }
        binding.noButton.setOnClickListener {
            onCancelClick.invoke()
            dismiss()
        }
         show()
    }
}