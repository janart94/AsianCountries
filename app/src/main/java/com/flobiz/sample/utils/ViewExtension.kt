package com.flobiz.sample.utils

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.flobiz.sample.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.text.NumberFormat
import java.util.*

@BindingAdapter("imageSvgUrl")
fun setSvgImageUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideToVectorYou
            .init()
            .with(view.context)
            .setPlaceHolder(
                R.drawable.ic_placeholder_flag,
                R.drawable.ic_placeholder_flag_error
            )
            .load(Uri.parse(imageUrl), view)
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context).load(imageUrl).placeholder(R.drawable.ic_placeholder_flag).into(view)
    }
}

@BindingAdapter("population")
fun setPopulation(view: TextView, population: Long) {
    view.text = NumberFormat.getNumberInstance(Locale.getDefault()).format(population)
}



