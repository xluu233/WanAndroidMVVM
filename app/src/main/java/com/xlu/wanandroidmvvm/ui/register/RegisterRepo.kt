package com.xlu.wanandroidmvvm.ui.register

import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * des 登陆
 * @date 2020/7/9
 * @author zs
 */
class RegisterRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    fun register(username: String, password: String, rePassword: String, registerLiveData : MutableLiveData<Any>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .register(username,password,rePassword)
                    .data()
            },
            success = {
                registerLiveData.postValue(it)
                toast("注册成功")
            }
        )
    }

}