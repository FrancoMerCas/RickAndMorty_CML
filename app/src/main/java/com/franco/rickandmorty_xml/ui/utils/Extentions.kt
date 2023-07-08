package com.franco.rickandmorty_xml.ui.utils

import android.content.Context
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import coil.util.CoilUtils
import com.franco.rickandmorty_xml.R

fun ImageView.loadThumbnail(url: String, corner: Float, context: Context) {
    load(url) {
        okhttp3.OkHttpClient.Builder().cache(CoilUtils.createDefaultCache(context)).build()
        crossfade(false)
        placeholder(R.drawable.ic_launcher_foreground)
        transformations(RoundedCornersTransformation(corner))
    }
}