package com.xlu.wanandroidmvvm.ui.search

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.base_library.common.toast
import com.xlu.kotlinandretrofit.bean.Article

class SearchViewModel : BaseViewModel() {
    private val repo by lazy { SearchRepo(viewModelScope, errorLiveData) }


    val keyWord = ObservableField<String>().apply {
        set("")
    }

    /**
     * 搜索到的文章
     */
    val articleLiveData = MutableLiveData<MutableList<Article.Data>>()

    /**
     * 收藏
     */
    val collectLiveData = MutableLiveData<Int>()

    /**
     * 取消收藏
     */
    val unCollectLiveData = MutableLiveData<Int>()

    /**
     * 是否为刷新或者首次加载
     */
    fun search(isRefresh: Boolean) {
        if (TextUtils.isEmpty(keyWord.get())) {
            toast("请输入关键字")
            return
        }
        repo.search(
            isRefresh,
            keyWord.get()!!,
            articleLiveData,
            emptyLiveDate
        )
    }

    /**
     * 收藏
     */
    fun collect(id:Int){
        repo.collect(id,collectLiveData)
    }

    /**
     * 取消收藏
     */
    fun unCollect(id:Int){
        repo.unCollect(id,unCollectLiveData)
    }

}