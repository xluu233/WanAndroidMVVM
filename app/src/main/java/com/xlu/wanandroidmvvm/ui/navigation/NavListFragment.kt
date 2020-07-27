package com.xlu.wanandroidmvvm.ui.navigation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.smartConfig
import com.xlu.base_library.common.smartDismiss
import com.xlu.base_library.common.toast
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.NavigationAdapter
import com.xlu.wanandroidmvvm.adapter.Navigation_NavAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentNavlistBinding

class NavListFragment : LazyFragment() {

    companion object {
        fun newInstance() = NavListFragment()
    }

    private lateinit var viewModel: NavListViewModel
    private lateinit var binding:FragmentNavlistBinding
    private var type = 0
    lateinit var smartRefreshLayout:SmartRefreshLayout
    lateinit var list:RecyclerView

    val adapter_project = NavigationAdapter(mutableListOf())
    val adapter_navigation = Navigation_NavAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavlistBinding.inflate(inflater,container,false)
        viewModel = ViewModelProviders.of(this).get(NavListViewModel::class.java)
        return binding.root
    }


    override fun lazyInit() {
        type = arguments?.getInt("type") ?: 0
        //toast(type.toString())
        initView()
        initAdapter()
    }

    override fun initView() {
        smartRefreshLayout = binding.smartRefresh
        list = binding.list

        smartRefreshLayout.setEnableLoadMore(false)
        smartRefreshLayout.setOnRefreshListener {
            if (type == Constants.NAV_PROJECT){
                viewModel.getProject()
            }else{
                viewModel.getNav()
            }
        }

        smartRefreshLayout.autoRefresh()
        //smartConfig(smartRefreshLayout)

    }

    private fun initAdapter() {
        list.layoutManager = LinearLayoutManager(context)
        if (type == Constants.NAV_PROJECT){
            list.adapter = adapter_project
            adapter_project.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)
            adapter_project.setListener(object : NavigationAdapter.clickListener{
                override fun clicked(position: Int, id: Int, name: String) {
                    toast(name)
                }
            })
        }else{
            list.adapter = adapter_navigation
            adapter_navigation.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)
            adapter_navigation.setListener(object : Navigation_NavAdapter.clickListener{
                override fun clicked(position: Int, id: Int, name: String, link: String) {
                    toast(link)
                }
            })
        }

    }

    override fun observe() {
        viewModel.liveData.observe(this, Observer {
            //体系
            adapter_project.setList(it)
            smartDismiss(smartRefreshLayout)
        })

        viewModel.liveData2.observe(this, Observer {
            //导航
            adapter_navigation.setList(it)
            smartDismiss(smartRefreshLayout)
        })

    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_navlist
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_navlist, viewModel).addBindingParam(1, viewModel)
    }


}