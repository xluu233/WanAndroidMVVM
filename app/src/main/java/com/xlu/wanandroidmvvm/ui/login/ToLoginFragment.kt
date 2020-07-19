package com.xlu.wanandroidmvvm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.wanandroidmvvm.R
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
        val viewPager:ViewPager2 = binding.loginViewpager
        val tabLayout:TabLayout = binding.loginTab

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
    }



    override fun getLayoutId(): Int? {
        return R.layout.fragment_tologin
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }


}

