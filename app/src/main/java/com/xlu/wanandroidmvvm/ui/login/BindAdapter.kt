package com.xlu.wanandroidmvvm.ui.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindAdapter {

    /**
     * 是否显示密码，图片
     */
    @BindingAdapter(value = ["passSrc"])
    @JvmStatic
    fun passSrc(view: ImageView, isVisibility: Boolean) {
        view.isSelected = isVisibility
    }

    /**
     * 是否显示密码，edit
     */
    @BindingAdapter(value = ["passType"])
    @JvmStatic
    fun passType(view: EditText, isVisibility: Boolean) {
        view.transformationMethod = if (isVisibility) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }
}