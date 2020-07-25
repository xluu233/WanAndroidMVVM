package com.xlu.wanandroidmvvm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter.AnimationType
import com.like.LikeButton
import com.like.OnLikeListener
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.smartConfig
import com.xlu.base_library.common.smartDismiss
import com.xlu.base_library.common.toast
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.RecyclerDataBindingAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentHomeBinding
import com.xlu.wanandroidmvvm.utils.LoginUtl
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : LazyFragment(){

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding

    private var page = 0
    private var bannerList = mutableListOf<String>()
    private var bannerLinkList = mutableListOf<String>()
    //private var articleList = mutableListOf<Article.Data>()
    private val listAdapter = RecyclerDataBindingAdapter()
    private var isRefresh:Boolean = false

    private val head by lazy {
        LayoutInflater.from(mActivity).inflate(R.layout.view_banner, null)
    }
    private val banner by lazy {
        head.findViewById(R.id.banner) as Banner<*, *>
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

    override fun observe() {
        //banner数据
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
//            Log.d("image",bannerList.toString())
//            Log.d("image",bannerLinkList.toString())
            initBanner()
            smartDismiss(binding.smartRefresh)
        })
        //文章数据
        homeViewModel.articleList.observe(this, Observer {
            Log.d("articleSize:","${it.size}")
            smartDismiss(binding.smartRefresh)
            if (isRefresh) listAdapter.setList(it) else listAdapter.addData(it)
        })
        //请求错误
        homeViewModel.errorLiveData.observe(this, Observer {
            smartDismiss(binding.smartRefresh)
        })
        //收藏
        homeViewModel.collectLiveData.observe(this, Observer {
            //listAdapter.collect(it)
        })
        //取消收藏
        homeViewModel.unCollectLiveData.observe(this, Observer {
            //listAdapter.unCollec(it)
        })

    }

    override fun lazyInit() {
        initView()
        initAdapter()
    }

    private fun initAdapter() {
        binding.homeList.isNestedScrollingEnabled = false
        binding.homeList.layoutManager = LinearLayoutManager(context)
        binding.homeList.adapter = listAdapter
        //add Banner
        listAdapter.addHeaderView(head)
        //set Anim
        listAdapter.setAnimationWithDefault(AnimationType.AlphaIn)
        //listAdapter.setListener(this@HomeFragment)
        listAdapter.setListener(object : RecyclerDataBindingAdapter.likeListener {
            override fun unlike(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@HomeFragment.listAdapter.data[position-1].apply {
                        homeViewModel.unCollect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }

            override fun like(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@HomeFragment.listAdapter.data[position-1].apply {
                        homeViewModel.collect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }
        })

    }

/*    override fun onItemChildClick(adapter: RecyclerDataBindingAdapter, view: View, position: Int) {
        when(view.id){
            R.id.ivLike->{
                if (LoginUtl.isLogin()){
                    object : OnLikeListener{
                        override fun liked(likeButton: LikeButton?) {
                            toast("collect")
                            this@HomeFragment.listAdapter.data[position].apply {
                                homeViewModel.collect(id)
                            }
                        }

                        override fun unLiked(likeButton: LikeButton?) {
                            toast("cancle")
                            this@HomeFragment.listAdapter.data[position].apply {
                                homeViewModel.unCollect(position)
                            }
                        }

                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }

            }

        }
    }*/

    override fun initView() {
        binding.smartRefresh.autoRefresh()
        binding.smartRefresh.setOnRefreshListener {
            isRefresh = true
            homeViewModel.getArticleList(true)
        }
        binding.smartRefresh.setOnLoadMoreListener {
            isRefresh = false
            page++
            homeViewModel.getArticleList(false)
        }
        smartConfig(binding.smartRefresh)
        setNoRepeatClick(binding.add,binding.clSearch){
            when(it.id){
                //分享文章
                //R.id.add ->
                //搜索
                R.id.clSearch -> nav().navigate(R.id.main_to_search)
            }
        }

    }



    private fun initBanner() {
        /*方式一：直接在布局中写好*/
/*        val banner = binding.homeBanner
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

        }*/

        /*方式二：通过在recyclerview-adapter中添加header*/
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