package com.xlu.wanandroidmvvm.ui.tab

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.initFragment
import com.xlu.base_library.common.toast
import com.xlu.kotlinandretrofit.bean.ProjectClassification
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.databinding.FragmentTabBinding
import com.xlu.wanandroidmvvm.ui.articleList.ArticleListFragment
import com.xlu.wanandroidmvvm.ui.banner.DataBean
import com.xlu.wanandroidmvvm.ui.banner.ImageAdapter
import com.xlu.wanandroidmvvm.ui.login.LoginFragment
import com.xlu.wanandroidmvvm.ui.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_home.*

class TabFragment : LazyFragment() {

    companion object {
        fun newInstance() = TabFragment()
    }

    private lateinit var viewModel: TabViewModel
    private lateinit var binding:FragmentTabBinding
    private var type:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentTabBinding.inflate(inflater,container,false)
        viewModel = ViewModelProviders.of(this).get(TabViewModel::class.java)
        return binding.root
    }

    override fun observe() {
        viewModel.tabLiveData.observe(this, Observer {
            initViewPager(it)
        })
    }

    private fun initViewPager(it: MutableList<ProjectClassification>?) {
        val viewPager = binding.tabViewpager
        val tabLayout = binding.tabTab

/*        viewPager.initFragment(this, arrayListOf<Fragment>().apply {
            it!!.forEach {
                add(ArticleListFragment().apply {
                    val bundle = Bundle()
                    bundle.putInt("type", type)
                    bundle.putInt("tabId", it.id)
                    bundle.putString("name", it.name)
                    arguments = bundle
                })
            }
        })*/

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = it!!.size
            override fun createFragment(position: Int): Fragment {
                //return ArticleListFragment.newInstance()
                return ArticleListFragment.newInstance().apply {
                    val bundle = Bundle()
                    bundle.putInt("type", type)
                    bundle.putInt("tabId", it!![position].id)
                    bundle.putString("name", it!![position].name)
                    arguments = bundle
                }
            }
        }
        viewPager.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                else -> tab.text = it!![position].name
            }
        }.attach()

    }

    override fun lazyInit() {
        arguments?.apply {
            type = getInt("type")
        }
        viewModel.getTab(type)
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_tab
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_tab, viewModel).addBindingParam(1, viewModel)
    }

}