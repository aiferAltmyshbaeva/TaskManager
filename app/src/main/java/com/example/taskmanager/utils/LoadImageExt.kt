package com.example.taskmanager.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.taskmanager.R

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_profile).into(this)
}