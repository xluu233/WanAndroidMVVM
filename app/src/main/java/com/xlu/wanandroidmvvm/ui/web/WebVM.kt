package com.zs.zs_jetpack.ui.web

import androidx.databinding.ObservableField
import com.xlu.base_library.base.BaseViewModel

class WebVM: BaseViewModel() {

    /**
     * webView 进度
     */
    val progress = ObservableField<Int>()


    /**
     * 最大 进度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * progress是否隐藏
     */
    val isVisible = ObservableField<Boolean>()

}