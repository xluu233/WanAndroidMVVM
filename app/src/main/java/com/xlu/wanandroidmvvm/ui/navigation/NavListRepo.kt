package com.xlu.wanandroidmvvm.ui.navigation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.kotlinandretrofit.bean.NavigationEntity
import com.xlu.kotlinandretrofit.bean.SystemBean
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * @Author xlu
 * @Date 2020/7/22 23:18
 * @Description TODO
 */
class NavListRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) : BaseRepository(coroutineScope, errorLiveData) {


    //体系
    fun getProjectList(
        liveData: MutableLiveData<MutableList<SystemBean>>
    ){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .getSystemList()
                    .data()

            },
            success = {
                liveData.postValue(it)
            },
            error = {
                toast("???")
            }
        )
    }


    //导航
    fun getNavList(
        liveData: MutableLiveData<MutableList<NavigationEntity>>
    ){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .getNavigation()
                    .data()

            },
            success = {
                liveData.postValue(it)
            }
        )
    }


}