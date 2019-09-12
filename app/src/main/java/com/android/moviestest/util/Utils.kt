package com.android.moviestest.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

object Utils {

    fun ImageView.setImageURI(uri: String) {
        Picasso.get().load(uri).into(this)
    }
}