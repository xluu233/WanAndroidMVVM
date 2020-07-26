package com.xlu.wanandroidmvvm.ui.search

import android.animation.ValueAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.common.*
import com.xlu.base_library.utils.KeyBoardUtil
import com.xlu.base_library.utils.ScreenUtils
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.adapter.RecyclerDataBindingAdapter
import com.xlu.wanandroidmvvm.databinding.FragmentSearchBinding
import com.xlu.wanandroidmvvm.ui.login.LoginViewModel
import com.xlu.wanandroidmvvm.utils.LoginUtl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    private val listAdapter = RecyclerDataBindingAdapter()
    private var isRefresh:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //自定义返回
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startSearchAnim(false)
                    val disposable = Single.timer(250, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            nav().navigateUp()
                        }, {})
                    KeyBoardUtil.closeKeyboard(binding.etSearch, mActivity)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getFragmentViewModel(SearchViewModel::class.java)
        //viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun observe() {
        viewModel.articleLiveData.observe(this, Observer {
            smartDismiss(binding.smartRefresh)
            if (!isRefresh) listAdapter.setList(it) else listAdapter.addData(it)
        })
        //收藏
        viewModel.collectLiveData.observe(this, Observer {
            //adapter.collectNotifyById(it)
        })
        //取消收藏
        viewModel.unCollectLiveData.observe(this, Observer {
            //adapter.unCollectNotifyById(it)
        })
        viewModel.emptyLiveDate.observe(this, Observer {
            //显示为空
        })
        viewModel.errorLiveData.observe(this, Observer {
            smartDismiss(binding.smartRefresh)
            if (it.errorCode == -100) {
                //显示网络错误
            }
        })
    }

    override fun init(savedInstanceState: Bundle?) {
        startSearchAnim(true)
        initView()
        initAdapter()
    }

    override fun initView() {
        smartConfig(binding.smartRefresh)
        binding.smartRefresh.setOnRefreshListener {
            search(true)
        }
        binding.smartRefresh.setOnLoadMoreListener {
            search(false)
        }

        //editText获取焦点
        binding.etSearch.requestFocus()
        KeyBoardUtil.openKeyboard(binding.etSearch, mActivity)
        binding.etSearch.doAfterTextChanged {
            val keyWord = binding.etSearch.text.toString()
            if (TextUtils.isEmpty(keyWord)) {
                //隐藏清除按钮
                binding.ivClear.visibility = View.GONE
            } else {
                //显示清除按钮
                binding.ivClear.visibility = View.VISIBLE
            }
            //将关键字空格去除
            viewModel.keyWord.set(keyWord.trim { it <= ' ' })
        }

        binding.etSearch.keyBoardSearch {
            //toast(viewModel.keyWord.get().toString()+"?")
            search(true)
        }
        binding.ivClear.setOnClickListener { binding.etSearch.setText("") }

    }

    private fun initAdapter() {
        binding.rvSearch.isNestedScrollingEnabled = false
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = listAdapter

        listAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)
        listAdapter.setListener(object : RecyclerDataBindingAdapter.likeListener {
            override fun unlike(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@SearchFragment.listAdapter.data[position-1].apply {
                        viewModel.unCollect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }

            override fun like(data: Article.Data?, position: Int) {
                if (LoginUtl.isLogin()){
                    this@SearchFragment.listAdapter.data[position-1].apply {
                        viewModel.collect(id)
                    }
                }else{
                    nav().navigate(R.id.main_to_login)
                }
            }
        })

        listAdapter.setOnItemClickListener { adapter, view, position ->
            nav().navigate(R.id.search_to_web,this@SearchFragment.listAdapter.getBundle(position))
        }
    }



    private fun search(isRefresh:Boolean) {
        this.isRefresh = isRefresh
        viewModel.search(isRefresh)
        KeyBoardUtil.closeKeyboard(binding.etSearch, mActivity)
    }


    /**
     * 开启搜索动画
     * @param isIn 是否为进
     */
    private fun startSearchAnim(isIn: Boolean) {
        //搜索按钮初始宽度为：屏幕宽度-140dp
        val searchWidth = ScreenUtils.getScreenWidth(mActivity) - dip2px(mActivity, 140f)
        //搜索按钮目标宽度为：屏幕宽度-32dp
        val targetWidth = ScreenUtils.getScreenWidth(mActivity) - dip2px(mActivity, 32f)

        //进入
        val anim = if (isIn) {
            ValueAnimator.ofInt(searchWidth, targetWidth)
        }
        //退出
        else {
            ValueAnimator.ofInt(targetWidth, searchWidth)
        }
        anim.duration = 249
        anim.addUpdateListener {
            val value = it.animatedValue as Int
            //平滑的，动态的设置宽度
            val params = binding.clSearch.layoutParams as ViewGroup.MarginLayoutParams
            params.width = value
            binding.clSearch.layoutParams = params
        }
        anim.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        KeyBoardUtil.closeKeyboard(binding.etSearch, mActivity)
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_search
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_search, viewModel).addBindingParam(1, viewModel)
    }



}