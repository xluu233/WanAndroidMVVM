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

    //搜索记录
    private var recordList: MutableList<String>? = null
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
            if (isRefresh) listAdapter.setList(it) else listAdapter.addData(it)
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
        initAdapter()
        initView()
    }

    override fun initView() {
        binding.smartRefresh.autoRefresh()
        binding.smartRefresh.setOnRefreshListener {
            isRefresh = true
            search()
        }
        binding.smartRefresh.setOnLoadMoreListener {
            isRefresh = false
            search()
        }
        smartConfig(binding.smartRefresh)

        //editText获取焦点
        binding.etSearch.requestFocus()
        KeyBoardUtil.openKeyboard(binding.etSearch, mActivity)
        addListener()
    }

    private fun initAdapter() {
        binding.rvSearch.isNestedScrollingEnabled = false
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = listAdapter
        //set Anim
        listAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)
        //listAdapter.setListener(this@HomeFragment)
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

    }

    /**
     * 为EditText添加监听事件，以及搜索按钮
     */
    private fun addListener() {
        //搜索框监听事件
        binding.etSearch.doAfterTextChanged {
            //搜索框为空的时候显示搜索历史
            if (TextUtils.isEmpty(viewModel.keyWord.get()!!)) {
                loadRecord()
                //隐藏清除按钮
                binding.ivClear.visibility = View.GONE

                binding.smartRefresh.visibility = View.GONE
                binding.clRecord.visibility = View.VISIBLE
            } else {
                //显示清除按钮
                binding.ivClear.visibility = View.VISIBLE
                binding.etSearch.setSelection(viewModel.keyWord.get()!!.length)
            }
        }
        //添加搜索按钮
        binding.etSearch.keyBoardSearch {
            //将关键字空格去除
            val keyWord = viewModel.keyWord.get()!!.trim { it <= ' ' }
            //如果关键字部位null或者""
            if (!TextUtils.isEmpty(keyWord)) {
                //将已存在的key移除，避免存在重复数据
                for (index in 0 until recordList?.size!!) {
                    if (recordList!![index] == keyWord) {
                        recordList!!.removeAt(index)
                        break
                    }
                }
                recordList?.add(keyWord)
                isRefresh = true
                search()
            }
        }
    }


    /**
     * 加载搜索tag
     */
    private fun loadRecord() {
        binding.labelsView.setLabels(recordList) { _, _, data ->
            data
        }
        //不知为何，在xml中设置主题背景无效
        for (child in binding.labelsView.children){
            child.setBackgroundResource(R.drawable.ripple_tag_bg)
        }
        //标签的点击监听
        binding.labelsView.setOnLabelClickListener { _, data, _ ->
            if (data is String) {
                viewModel.keyWord.set(data)
                isRefresh = true
                search()
            }
        }
    }


    private fun search() {
        binding.clRecord.visibility = View.GONE
        smartRefresh.visibility = View.VISIBLE
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



    override fun getLayoutId(): Int? {
        return R.layout.fragment_search
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_search, viewModel).addBindingParam(1, viewModel)
    }



}