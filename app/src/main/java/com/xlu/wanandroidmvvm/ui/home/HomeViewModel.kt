package com.xlu.wanandroidmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.kotlinandretrofit.bean.Banner

class HomeViewModel : BaseViewModel() {
    private val repo by lazy { HomeRepo(viewModelScope,errorLiveData) }

    /**
     * 文章列表
     */
    val articleList = MutableLiveData<MutableList<Article.Data>>()

    /**
     * banner
     */
    val banner = MutableLiveData<MutableList<Banner>>()

    /**
     * 收藏
     */
    val collectLiveData = MutableLiveData<Any>()

    /**
     * 取消收藏
     */
    val unCollectLiveData = MutableLiveData<Any>()

    /**
     * 获取首页文章列表， 包括banner
     */
    fun getArticleList(isRefresh:Boolean) {
        repo.getArticleList(isRefresh,articleList,banner)
    }

    fun collect(id:Int){
        repo.collect(id,collectLiveData)
    }

    fun unCollect(id:Int){
        repo.unCollect(id,unCollectLiveData)
    }

}