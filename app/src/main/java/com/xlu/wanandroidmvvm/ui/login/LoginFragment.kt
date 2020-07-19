package com.xlu.wanandroidmvvm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment() {


    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun init(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_login
    }

    override fun initViewModel() {
        viewModel = getFragmentViewModel(LoginViewModel::class.java)
        super.initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_login, viewModel)
            .addBindingParam(1, viewModel)
    }

}