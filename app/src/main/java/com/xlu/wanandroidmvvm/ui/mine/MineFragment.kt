package com.xlu.wanandroidmvvm.ui.mine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.base.LazyFragment
import com.xlu.base_library.common.setNoRepeatClick
import com.xlu.base_library.common.toast
import com.xlu.base_library.utils.PrefUtils
import com.xlu.kotlinandretrofit.bean.Coin
import com.xlu.kotlinandretrofit.bean.Userbean
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.databinding.FragmentMineBinding
import com.xlu.wanandroidmvvm.eventbus.LoginEvent
import com.xlu.wanandroidmvvm.eventbus.LogoutEvent
import com.xlu.wanandroidmvvm.utils.LoginUtl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MineFragment : LazyFragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    private lateinit var viewModel: MineViewModel
    private lateinit var binding: FragmentMineBinding
    private var coin:Coin? = null
    private var userbean:Userbean? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventBus.getDefault().register(this)
        binding = FragmentMineBinding.inflate(inflater,container,false)
        return binding.root

    }

    /**
     * 登陆消息,收到消息请求个人信息接口
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginEvent(loginEvent: LoginEvent) {
        viewModel.getInfo()
    }

    /**
     * 退出消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun logoutEvent(loginEvent: LogoutEvent) {
        viewModel.nickname.set("点击登录")
        viewModel.id.set("---")
        viewModel.coin.set("0")
        viewModel.rank.set("0")
        viewModel.level.set("0")

        /*只好手动设置了*/
        binding.nickname.setText("点击登录")
        binding.id.setText("")
        binding.coin.setText("0")
        binding.rangking.setText("0")
        //binding.icon.setImageDrawable(resources.getDrawable(R.drawable.ic_icon))

    }

    override fun observe() {
        viewModel.liveData.observe(this, Observer {
            coin = it
            setDataBinding()
        })
        viewModel.logoutLiveData.observe(this, Observer {
            Log.d("logout","success")
        })
    }

    private fun setDataBinding() {
        /*这里databinding不生效了，不知道为什么？*/
        viewModel.nickname.set("${coin?.username}")
        viewModel.id.set("${coin?.userId}")
        viewModel.coin.set("${coin?.coinCount}")
        viewModel.rank.set("${coin?.rank}")
        viewModel.level.set("${coin?.level}")

        /*只好手动设置了*/
        binding.nickname.setText("${coin?.username}")
        binding.id.setText("id:${coin?.userId}")
        binding.coin.setText("${coin?.coinCount}")
        binding.rangking.setText("${coin?.rank}")
/*        context?.let {
            Glide.with(it)
                .load(userbean?.icon)
                .into(binding.icon)
        }*/

    }

    override fun lazyInit() {
        //先判断数据是否为空，然后再强转，否则会出异常
        PrefUtils.getObject(Constants.INTEGRAL_INFO)?.let {
            //先从本地获取积分，获取不到再通过网络获取
            coin = it as Coin?
        }
        PrefUtils.getObject(Constants.USER_INFO)?.let {
            userbean = it as Userbean?
        }
        if (coin == null) {
            if (LoginUtl.isLogin()) {
                viewModel.getInfo()
            }
        }
        //setDataBinding()
    }

    override fun onClick() {
        setNoRepeatClick(binding.rankList,binding.icon,binding.nickname,
            binding.myCoin,binding.myCollection,binding.myArticle,binding.mySetting,binding.myShare){
            when(it){
                binding.rangking -> {
                    //跳转排行榜
                }
                binding.icon -> {
                    //修改头像
                }
                binding.nickname -> {
                    //登录
                    if (!LoginUtl.isLogin()){
                        nav().navigate(R.id.main_to_login)
                    }
                }
                binding.myCoin ->{
                    //积分详情

                }
                binding.myCollection ->{
                    //我的收藏
                }
                binding.myShare ->{
                    //我的分享

                }
                binding.myArticle ->{
                    //我的文章

                }
                binding.mySetting ->{
                    //设置
                    toast("设置")

                }
                binding.logout ->{
                    //退出登录
                    if (LoginUtl.isLogin()) {
                        viewModel.logout()
                        toast("请先登录？？")
                    }else{
                        toast("请先登录")
                    }
                }
            }
        }
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_mine
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_mine, viewModel).addBindingParam(1, viewModel)
    }

    override fun initViewModel() {
        viewModel = getFragmentViewModel(MineViewModel::class.java)
        super.initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

}