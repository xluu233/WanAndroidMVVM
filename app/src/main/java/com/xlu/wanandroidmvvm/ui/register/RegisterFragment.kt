package com.xlu.wanandroidmvvm.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.toast
import com.xlu.base_library.utils.KeyBoardUtil
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun init(savedInstanceState: Bundle?) {
        initView()
    }

    override fun initView() {
        setNoRepeatClick(ivPasswordVisibility,ivRePasswordVisibility,rlRegister,ivClear){
            when(it.id){
                R.id.ivClear -> viewModel.username.set("")
                R.id.ivPasswordVisibility -> viewModel.ispasswordVisbility.set(!viewModel.ispasswordVisbility.get()!!)
                R.id.ivRePasswordVisibility -> viewModel.isrepasswordVisbility.set(!viewModel.isrepasswordVisbility.get()!!)
                R.id.rlRegister -> {
                    //关闭软键盘
                    KeyBoardUtil.closeKeyboard(etUsername,mActivity)
                    KeyBoardUtil.closeKeyboard(etPassword,mActivity)
                    KeyBoardUtil.closeKeyboard(etRePassword,mActivity)
                    if (viewModel.username.get()!!.isEmpty()){
                        toast("请填写用户名")
                        return@setNoRepeatClick
                    }
                    if (viewModel.password.get()!!.isEmpty()){
                        toast("请填写密码")
                        return@setNoRepeatClick
                    }
                    if (viewModel.repassword.get()!!.isEmpty()){
                        toast("请填写确认密码")
                        return@setNoRepeatClick
                    }
                    viewModel.register()
                }
            }
        }
        super.initView()
    }


    override fun getLayoutId(): Int? {
        return R.layout.fragment_register
    }

    override fun initViewModel() {
        viewModel = getFragmentViewModel(RegisterViewModel::class.java)
        super.initViewModel()
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_register, viewModel).addBindingParam(1, viewModel)
    }

}