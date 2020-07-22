package com.xlu.wanandroidmvvm.ui.login

import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.base_library.utils.PrefUtils
import com.xlu.kotlinandretrofit.bean.Userbean
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.eventbus.LoginEvent
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import org.greenrobot.eventbus.EventBus

class LoginRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) : BaseRepository(coroutineScope, errorLiveData) {

    fun login(username: String, password: String,loginLiveData : MutableLiveData<Userbean>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .login(username,password)
                    .data()
            },
            success = {
                //登陆成功保存用户信息，并发送消息
                PrefUtils.setObject(Constants.USER_INFO,it)
                //更改登陆轧辊台
                PrefUtils.setBoolean(Constants.LOGIN,true)
                //发送登陆消息
                toast("登录成功")
                EventBus.getDefault().post(LoginEvent())
                loginLiveData.postValue(it)
            }
        )
    }

}