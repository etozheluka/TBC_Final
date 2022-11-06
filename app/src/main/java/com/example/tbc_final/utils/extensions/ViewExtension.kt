package com.example.tbc_final.utils.extensions

import android.view.View

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.INVISIBLE
}

fun View.goAway(){
    this.visibility = View.GONE
}