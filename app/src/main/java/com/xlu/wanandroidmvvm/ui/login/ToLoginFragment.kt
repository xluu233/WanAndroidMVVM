package com.xlu.wanandroidmvvm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.DataBean
import com.xlu.wanandroidmvvm.adapter.ImageAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentTologinBinding
import com.xlu.wanandroidmvvm.ui.register.RegisterFragment

class ToLoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = ToLoginFragment()
    }

    private lateinit var binding: FragmentTologinBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTologinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init(savedInstanceState: Bundle?) {
        val viewPager = binding.loginViewpager
        val tabLayout = binding.loginTab
        val banner = binding.loginBanner

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> LoginFragment.newInstance()
                    else -> RegisterFragment.newInstance()
                }
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "登录"
                else -> tab.text = "注册"
            }
        }.attach()


        //给banner重新设置数据
        banner.setAdapter(ImageAdapter(DataBean.getLoginBannerData()))
        banner.setLoopTime(5000)
        //banner.indicator = CircleIndicator(context)
        //banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
        //TabLayoutMediator(tabLayout, banner)

    }


    override fun getLayoutId(): Int? {
        return R.layout.fragment_tologin
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }


}


