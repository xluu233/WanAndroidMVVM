package com.xlu.wanandroidmvvm.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseViewModel
import com.xlu.kotlinandretrofit.bean.Userbean

class LoginViewModel : BaseViewModel() {

   val username = ObservableField<String>().apply {
      set("")
   }

   val password = ObservableField<String>().apply {
      set("")
   }

   //密码可见性
   val passIsVisibility = ObservableField<Boolean>().apply {
      set(false)
   }

   /**
    * 登陆
    */
   val loginLiveData = MutableLiveData<Userbean>()

   private val repo by lazy { LoginRepo(viewModelScope,errorLiveData) }

   fun login(){
      repo.login(username.get()!!,password.get()!!,loginLiveData)
   }

}
