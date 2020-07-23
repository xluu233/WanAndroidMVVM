package com.xlu.base_library.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * @Author xlu
 * @Date 2020/7/23 17:19
 * @Description TODO
 */
object CustomBindAdapter {

    /**
     * 加载资源图片
     */
    @BindingAdapter(value = ["imgSrc"])
    @JvmStatic
    fun imgSrc(view: ImageView, id: Int) {
        view.setImageResource(id)
    }

    /**
     * 加载图片
     */
    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        view.loadUrl(view.context.applicationContext,url)
    }


}

