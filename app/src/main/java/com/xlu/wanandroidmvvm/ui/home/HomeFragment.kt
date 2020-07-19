package com.xlu.wanandroidmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.wanandroidmvvm.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun init(savedInstanceState: Bundle?) {
        login.setOnClickListener { nav().navigate(R.id.main_to_login) }
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_home
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }
}