package com.example.tbc_final.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show()
}