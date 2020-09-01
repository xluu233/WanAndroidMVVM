package com.xlu.wanandroidmvvm.ui.login

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.toast
import com.xlu.base_library.utils.KeyBoardUtil
import com.xlu.wanandroidmvvm.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : LazyFragment(){

    private lateinit var loginViewModel: LoginViewModel

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_login
    }

    override fun lazyInit() {
        initView()
    }

    override fun initViewModel() {
        loginViewModel = getFragmentViewModel(LoginViewModel::class.java)
    }

    override fun observe() {
        loginViewModel.loginLiveData.observe(this, Observer {
            nav().navigateUp()
        })
    }

    override fun initView() {
        setNoRepeatClick(etPassword,etUsername,ivClear,ivPasswordVisibility,llLogin) {
            when (it.id) {
                //清除账号
                R.id.ivClear -> {
                    loginViewModel.username.set("")
                    loginViewModel.password.set("")

/*                    viewModel.username.postValue("")
                    viewModel.password.postValue("")*/

                }
                //密码是否可见
                R.id.ivPasswordVisibility -> {
                    //true false 切换
                    loginViewModel.passIsVisibility.set(!loginViewModel.passIsVisibility.get()!!)
                }
                //登陆
                R.id.llLogin -> {
                    //关闭软键盘
                    KeyBoardUtil.closeKeyboard(etUsername,mActivity)
                    KeyBoardUtil.closeKeyboard(etPassword,mActivity)

                    Log.d("tag","username is ${loginViewModel.username.get()},password is ${loginViewModel.password.get()}" )
                    if (loginViewModel.username.get()!!.isEmpty()){
                        toast("请填写用户名")
                        return@setNoRepeatClick
                    }
                    if (loginViewModel.password.get()!!.isEmpty()){
                        toast("请填写密码")
                        return@setNoRepeatClick
                    }

/*                    Log.d("tag","username is ${viewModel.username.value},password is ${viewModel.password.value}" )
                    if (viewModel.username.value!!.isEmpty()){
                        toast("请填写用户名")
                        return@setNoRepeatClick
                    }
                    if (viewModel.password.value!!.isEmpty()){
                        toast("请填写密码")
                        return@setNoRepeatClick
                    }*/

                    loginViewModel.login()
                }
            }
        }
    }


    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_login, loginViewModel).addBindingParam(1, loginViewModel)
    }



}