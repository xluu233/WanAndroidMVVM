package com.xlu.wanandroidmvvm.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.toast
import com.xlu.base_library.utils.KeyBoardUtil
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel



    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_login
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
    }

    override fun initViewModel() {
        viewModel = getFragmentViewModel(LoginViewModel::class.java)
        super.initViewModel()
    }


    override fun initView() {
        setNoRepeatClick(etPassword,etUsername,ivClear,ivPasswordVisibility,llLogin) {
            when (it.id) {
                //清除账号
                R.id.ivClear -> {
                    viewModel.username.set("")
                    viewModel.password.set("")
                }
                //密码是否可见
                R.id.ivPasswordVisibility -> {
                    //true false 切换
                    viewModel.passIsVisibility.set(!viewModel.passIsVisibility.get()!!)
                }
                //登陆
                R.id.llLogin -> {
                    //关闭软键盘
                    KeyBoardUtil.closeKeyboard(etUsername,mActivity)
                    KeyBoardUtil.closeKeyboard(etPassword,mActivity)
                    if (viewModel.username.get()!!.isEmpty()){
                        toast("请填写用户名")
                        return@setNoRepeatClick
                    }
                    if (viewModel.password.get()!!.isEmpty()){
                        toast("请填写密码")
                        return@setNoRepeatClick
                    }
                    Log.d("tag","username is ${viewModel.username},password is ${viewModel.password}" )
                    viewModel.login()
                }
            }
        }
    }


    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_login, viewModel).addBindingParam(1, viewModel)
    }

}