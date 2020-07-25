package com.xlu.wanandroidmvvm.common

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xlu.wanandroidmvvm.adapter.RecyclerDataBindingAdapter

interface OnChildItemClickListener {
    fun onItemChildClick(adapter: RecyclerDataBindingAdapter, view: View, position:Int )
}