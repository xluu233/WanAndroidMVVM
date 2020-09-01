package com.xlu.wanandroidmvvm.ui.mine

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.kotlinandretrofit.bean.Coin

class MineViewModel : BaseViewModel() {
    /*积分*/
    val coin = ObservableField<String>().apply { set("0") }
    /*排名*/
    val rank = ObservableField<String>().apply { set("0") }
    /*id*/
    val id = ObservableField<String>().apply { set("---") }
    /*昵称*/
    val nickname = ObservableField<String>().apply { set("点击登录") }
    /*等级*/
    val level = ObservableField<String>().apply { set("0") }
    /*头像*/
    val icon = ObservableField<String>().apply { set("") }

    private val repo by lazy { MineRepo(viewModelScope,errorLiveData) }
    val liveData = MutableLiveData<Coin>()

    fun getInfo(){
        repo.getCoin(liveData)
    }

    val logoutLiveData = MutableLiveData<Any>()

    fun logout(){
        repo.logout(logoutLiveData)
    }
}