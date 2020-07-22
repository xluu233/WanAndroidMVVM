package com.xlu.wanandroidmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.kotlinandretrofit.bean.Banner
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.DataBean
import com.xlu.wanandroidmvvm.adapter.ImageAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentHomeBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.RoundLinesIndicator
import com.youth.banner.util.BannerUtils

class HomeFragment : LazyFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding


    override fun lazyInit() {
        initBanner()


    }

    private fun initBanner() {
        val banner = binding.homeBanner
        

        banner.setAdapter(ImageAdapter(DataBean.getLoginBannerData()))
        banner.setLoopTime(5000)
        //banner.indicator = CircleIndicator(context)
        //banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
        //TabLayoutMediator(tabLayout, banner)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_home
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        TODO("Not yet implemented")
    }


}