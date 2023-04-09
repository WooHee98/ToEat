package com.example.toeat

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapterHelper {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context).load(it).override(1000,600) .into(view)
        }
    }
}