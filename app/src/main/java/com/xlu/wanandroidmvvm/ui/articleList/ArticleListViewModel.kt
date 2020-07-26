package com.xlu.wanandroidmvvm.ui.articleList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.base_library.common.toast
import com.xlu.kotlinandretrofit.bean.Article

class ArticleListViewModel : BaseViewModel() {

    private val repo by lazy { ArticleRepo(viewModelScope, errorLiveData) }

    val projectLiveData = MutableLiveData<MutableList<Article.Data>>()

    /**
     * 收藏
     */
    val collectLiveData = MutableLiveData<Int>()

    /**
     * 取消收藏
     */
    val unCollectLiveData = MutableLiveData<Int>()


    fun getProject(type: Int,tabId: Int,isRefresh: Boolean){
        repo.getProjectList(type, tabId, isRefresh, projectLiveData)
    }
    /**
     * 收藏
     */
    fun collect(id: Int) {
        repo.collect(id, collectLiveData)
    }

    /**
     * 取消收藏
     */
    fun unCollect(id: Int) {
        repo.unCollect(id, unCollectLiveData)
    }
}