package com.xlu.wanandroidmvvm

import android.annotation.SuppressLint
import android.content.Context
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.xlu.base_library.BaseApp


class App: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initSmartHead()
        instance = applicationContext
    }

    /**
     * 初始化加载刷新ui
     */
    private fun initSmartHead() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, _: RefreshLayout? ->
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, _: RefreshLayout? ->
            BallPulseFooter(context)
        }
    }

    companion object {
        lateinit var instance: Context
    }
}