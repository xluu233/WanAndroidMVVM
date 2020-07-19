package com.xlu.wanandroidmvvm.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.base.BaseViewModel
import com.xlu.base_library.http.ApiException
import com.xlu.base_library.utils.PrefUtils
import com.xlu.kotlinandretrofit.bean.Userbean
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

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

   val loginLiveData = MutableLiveData<Userbean>()

   private val repo by lazy { LoginRepo(viewModelScope,errorLiveData) }

   fun login(){
      repo.login(username.get()!!,password.get()!!,loginLiveData)
   }

}
