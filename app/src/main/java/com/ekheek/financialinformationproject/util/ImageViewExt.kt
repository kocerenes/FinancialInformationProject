package com.ekheek.financialinformationproject.util

import android.widget.ImageView
import coil.load
import com.ekheek.financialinformationproject.R

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(600)
        error(R.drawable.ic_error_placeholder)
    }
}