package com.xlu.wanandroidmvvm.adapter

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
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

}