package com.xlu.wanandroidmvvm.ui.articleList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.smartConfig
import com.xlu.base_library.common.smartDismiss
import com.xlu.base_library.common.toast
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.ArticleAdapter
import com.xlu.wanandroidmvvm.adapter.RecyclerDataBindingAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentArticlelistBinding
import com.xlu.wanandroidmvvm.utils.LoginUtl

class ArticleListFragment : LazyFragment() {

    companion object {
        fun newInstance() = ArticleListFragment()
    }

    private lateinit var viewModel: ArticleListViewModel
    private lateinit var binding:FragmentArticlelistBinding

    private var adapter = ArticleAdapter()
    private var type = 0
    private var tabId = 0
    private var isRefresh = true;

    override fun lazyInit() {
        type = arguments?.getInt("type") ?: 0
        tabId = arguments?.getInt("tabId") ?: 0
        initView()
        binding.smartRefresh.autoRefresh()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        binding = FragmentArticlelistBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun initView() {
        val smartRefresh = binding.smartRefresh
        val recyclerView = binding.list

        smartRefresh.setOnRefreshListener {
            isRefresh = true
            viewModel.getProject(type, tabId, true)
        }
        smartRefresh.setOnLoadMoreListener {
            isRefresh = false
            viewModel.getProject(type,tabId,false)
        }
        smartConfig(smartRefresh)


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)
        adapter.setListener(object : ArticleAdapter.likeListener {
            override fun unlike(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@ArticleListFragment.adapter.data[position].apply {
                        viewModel.unCollect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }

            override fun like(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@ArticleListFragment.adapter.data[position].apply {
                        viewModel.collect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }
        })

        adapter.setOnItemClickListener { adapter, view, position ->
            nav().navigate(R.id.main_to_web,this@ArticleListFragment.adapter.getBundle(position))
        }

    }

    override fun observe() {
        viewModel.projectLiveData.observe(this, Observer {
            smartDismiss(binding.smartRefresh)
            if (isRefresh) adapter.setList(it) else adapter.addData(it)
        })
    }


    override fun getLayoutId(): Int? {
        return R.layout.fragment_articlelist
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_articlelist, viewModel).addBindingParam(1, viewModel)
    }


}