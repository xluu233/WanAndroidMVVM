package com.xlu.wanandroidmvvm.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel

class RegisterViewModel : BaseViewModel() {
    /*账号*/
    val username = ObservableField<String>().apply { set("") }
    /*密码*/
    val password = ObservableField<String>().apply { set("") }
    /*确认密码*/
    val repassword = ObservableField<String>().apply { set("") }
    /*密码可见性*/
    val ispasswordVisbility = ObservableField<Boolean>().apply { set(true) }
    /*二次密码可见性*/
    val isrepasswordVisbility = ObservableField<Boolean>().apply { set(true) }

    /*注册*/
    val registerLiveData = MutableLiveData<Any>()
    val repo by lazy { RegisterRepo(viewModelScope,errorLiveData) }
    fun register(){
        repo.register(username.get()!!,password.get()!!,repassword.get()!!,registerLiveData)
    }
}