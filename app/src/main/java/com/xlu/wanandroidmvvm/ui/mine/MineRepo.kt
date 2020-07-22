package com.xlu.wanandroidmvvm.ui.mine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.base_library.utils.PrefUtils
import com.xlu.kotlinandretrofit.bean.Coin
import com.xlu.kotlinandretrofit.bean.Userbean
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

class MineRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {


    fun getCoin(liveData: MutableLiveData<Coin>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .getCoin()
                    .data()
            },
            success = {
                liveData.postValue(it)
            },
            error = {
                //toast("????")
            }
        )
    }


}