package com.jsmyth.githubtrends.binding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean?) {
        view.visibility = (if (value!!) View.VISIBLE else View.GONE)
    }
}
