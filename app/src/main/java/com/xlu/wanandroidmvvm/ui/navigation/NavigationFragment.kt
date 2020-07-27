package com.xlu.wanandroidmvvm.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.databinding.FragmentNavigationBinding
import com.xlu.wanandroidmvvm.ui.register.RegisterFragment

class NavigationFragment : BaseFragment(){

    private lateinit var binding:FragmentNavigationBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun init(savedInstanceState: Bundle?) {
        val viewPager = binding.viewpager
        val tabLayout = binding.tab

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    //体系
                    0 -> NavListFragment.newInstance().apply {
                        arguments = Bundle().apply {
                            putInt("type", Constants.NAV_PROJECT)
                        }
                    }
                    //导航
                    else -> NavListFragment.newInstance().apply {
                        arguments = Bundle().apply {
                            putInt("type", Constants.NAV_NAVIGATION)
                        }
                    }
                }
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "体系"
                else -> tab.text = "导航"
            }
        }.attach()

    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_navigation
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }
}