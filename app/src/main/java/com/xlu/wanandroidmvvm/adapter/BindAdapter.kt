package com.xlu.wanandroidmvvm.adapter

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindAdapter {



    /*视图可见性*/
    @BindingAdapter(value = ["isGone"])
    @JvmStatic
    fun isGone(view: View, visible: Boolean) {
        //true 或者 1 可见
        //false 0 不可见
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["isGoneByInt"])
    @JvmStatic
    fun isGoneByInt(view: View, visible: Int) {
        //true 或者 1 可见
        //false 0 不可见
        view.visibility = if (visible == 1) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageUrl")
    fun setSrc(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

}