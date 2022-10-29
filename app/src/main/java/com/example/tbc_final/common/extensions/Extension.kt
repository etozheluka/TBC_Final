package com.example.tbc_final.common.extensions

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


fun String.isValidEmail() =
    isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun ImageView.setImage(url: String){
    Glide.with(this).load(url).into(this)
}

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show()
}