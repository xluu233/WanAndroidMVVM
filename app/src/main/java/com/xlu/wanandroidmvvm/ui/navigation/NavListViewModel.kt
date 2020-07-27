package com.xlu.wanandroidmvvm.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.kotlinandretrofit.bean.NavigationEntity
import com.xlu.kotlinandretrofit.bean.SystemBean
import com.xlu.wanandroidmvvm.ui.articleList.ArticleRepo

class NavListViewModel : BaseViewModel() {

    private val repo by lazy {
        NavListRepo(viewModelScope,errorLiveData)
    }

    /*体系*/
    val liveData = MutableLiveData<MutableList<SystemBean>>()
    fun getProject(){
        repo.getProjectList(liveData)
    }


    /*导航*/
    val liveData2 = MutableLiveData<MutableList<NavigationEntity>>()
    fun getNav(){
        repo.getNavList(liveData2)
    }
}