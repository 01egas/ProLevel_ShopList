package com.example.prolevel_shoplist.presentation

import androidx.databinding.BindingAdapter
import com.example.prolevel_shoplist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(tilName: TextInputLayout, it: Boolean) {
    if (it) {
        tilName.error = tilName.context.getString(R.string.name_error)
    } else {
        tilName.error = null
    }
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(tilCount: TextInputLayout, it: Boolean) {
    if (it) {
        tilCount.error = tilCount.context.getString(R.string.count_error)
    } else {
        tilCount.error = null
    }
}



