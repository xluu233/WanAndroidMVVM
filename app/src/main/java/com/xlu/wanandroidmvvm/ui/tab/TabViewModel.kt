package com.xlu.wanandroidmvvm.ui.tab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.kotlinandretrofit.bean.ProjectClassification

class TabViewModel : BaseViewModel() {

    private val repo by lazy { TabRepo(viewModelScope,errorLiveData) }
    /**
     * tab
     */
    val tabLiveData = MutableLiveData<MutableList<ProjectClassification>>()

    fun getTab(type:Int){
        repo.getTab(type,tabLiveData)
    }
}