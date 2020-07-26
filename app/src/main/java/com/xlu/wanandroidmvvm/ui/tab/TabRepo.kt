package com.xlu.wanandroidmvvm.ui.tab

import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.isListEmpty
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.kotlinandretrofit.bean.ProjectClassification
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * @Author xlu
 * @Date 2020/7/26 0:57
 * @Description TODO
 */
class TabRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) : BaseRepository(coroutineScope,errorLiveData) {


    fun getTab(type: Int, tabLiveData: MutableLiveData<MutableList<ProjectClassification>>) {
        launch(
            block = {
                if (type == Constants.PROJECT_TYPE) {
                    //开源项目列表
                    RetrofitManager.getApiService(ApiService::class.java)
                        .getProjectClassification()
                        .data()
                } else {
                    //微信公总号列表
                    RetrofitManager.getApiService(ApiService::class.java)
                        .getAccountTabList()
                        .data()
                }
            },
            success = {
                tabLiveData.postValue(it)
            })

    }

}