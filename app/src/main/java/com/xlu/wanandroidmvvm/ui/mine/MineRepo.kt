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
import com.xlu.wanandroidmvvm.eventbus.LoginEvent
import com.xlu.wanandroidmvvm.eventbus.LogoutEvent
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import org.greenrobot.eventbus.EventBus

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

    fun logout(logoutLiveData: MutableLiveData<Any>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .logout()
                    .data()
            },
            success = {
                logoutLiveData.postValue(it)
                resetUser()
            }
        )
    }

    /**
     * 退出登录，重置用户状态
     */
    fun resetUser() {
        toast("已退出登录")
        //发送退出登录消息
        EventBus.getDefault().post(LogoutEvent())
        //更新登陆状态
        PrefUtils.setBoolean(Constants.LOGIN, false)
        //移除用户信息
        PrefUtils.removeKey(Constants.USER_INFO)
        //移除积分信息
        PrefUtils.removeKey(Constants.INTEGRAL_INFO)
    }


}