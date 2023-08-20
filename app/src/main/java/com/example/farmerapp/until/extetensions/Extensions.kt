package com.example.farmerapp.until.extetensions


import com.example.farmerapp.R

object Extensions {
    fun checkEditTextNullAndSetErrorStatus(
        editLayoutList: List<com.google.android.material.textfield.TextInputLayout>,
    ): Boolean {
        var check = true
        for (editLayout in editLayoutList) {
            val context = editLayout.context
            if (editLayout.editText!!.text.isNullOrEmpty()) {
                editLayout.error = context.getString(R.string.required)
                check = false
            } else {
                editLayout.error = null
            }
        }
        return check
    }
}
