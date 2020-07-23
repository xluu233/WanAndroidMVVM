package com.xlu.wanandroidmvvm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.smartConfig
import com.xlu.base_library.common.smartDismiss
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.RecyclerDataBindingAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentHomeBinding
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : LazyFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding

    private var bannerList = mutableListOf<String>()
    private var bannerLinkList = mutableListOf<String>()
    private var page = 0

    private val listAdapter = RecyclerDataBindingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun observe() {
        homeViewModel.banner.observe(this, Observer {
            bannerList.clear()
            bannerLinkList.clear()
            /*用listIterator()遍历会出现只遍历两次、不完整的情况，原因未知*/
/*            val listIterator = it.listIterator()
            while (listIterator.hasNext()){
                //Log.d("Index:","${listIterator.nextIndex()}")
                //Log.d("value:","${listIterator.next().imagePath}")
                bannerList.add(listIterator.next().imagePath)
                //bannerLinkList.add(listIterator.next().url)
            }*/

            for (item in it){
                bannerList.add(item.imagePath)
                bannerLinkList.add(item.url)
            }
            //Log.d("image",bannerList.toString())
            //Log.d("image",bannerLinkList.toString())
            initBanner()
            smartDismiss(binding.smartRefresh)
        })

        homeViewModel.articleList.observe(this, Observer {
            binding.homeList.layoutManager = LinearLayoutManager(context)
            binding.homeList.adapter = listAdapter
            listAdapter.setList(it)
        })


    }

    override fun lazyInit() {
        initView()
        //initBanner()
    }

    override fun initView() {
        binding.smartRefresh.autoRefresh()
        binding.smartRefresh.setOnRefreshListener {
            homeViewModel.getArticleList(true)
        }
        binding.smartRefresh.setOnLoadMoreListener {
            page++
            homeViewModel.getArticleList(false)
        }
        smartConfig(binding.smartRefresh)
        setNoRepeatClick(binding.add,binding.clSearch){
            when(it.id){
                //分享文章
                //R.id.add ->
                //搜索
                //R.id.clSearch ->
            }
        }
    }


    private fun initBanner() {
        val banner = binding.homeBanner
        val adapter = com.xlu.wanandroidmvvm.adapter.BannerImageAdapter(bannerList.toList())
        banner.let {
            it.addBannerLifecycleObserver(this)
            it.setIndicator(CircleIndicator(context))
            it.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
            it.setLoopTime(3000)
            //it.setBannerRound(20f)
            it.setBannerGalleryMZ(20);
            it.adapter = adapter
            it.setOnBannerListener { data: Any, position: Int ->
                //点击事件设置
                Log.d("position:","$position")
            }

        }

    }



    override fun getLayoutId(): Int? {
        return R.layout.fragment_home
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeViewModel).addBindingParam(1, homeViewModel)
    }


}