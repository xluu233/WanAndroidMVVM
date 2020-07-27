package com.xlu.wanandroidmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.xlu.base_library.base.BaseFragment
import com.xlu.base_library.base.DataBindingConfig
import com.xlu.base_library.common.initFragment
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.R
import com.xlu.wanandroidmvvm.ui.home.HomeFragment
import com.xlu.wanandroidmvvm.ui.mine.MineFragment
import com.xlu.wanandroidmvvm.ui.navigation.NavigationFragment
import com.xlu.wanandroidmvvm.ui.tab.TabFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private val fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }

    /**
     * 项目
     */
    private val projectFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type",Constants.PROJECT_TYPE)
            }
        }
    }

    /**
     * 广场
     */
    private val squareFragment by lazy { NavigationFragment() }

    /**
     * 公众号
     */
    private val publicNumberFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type",Constants.ACCOUNT_TYPE)
            }
        }
    }

    /**
     * 我的
     */
    private val mineFragment by lazy { MineFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(projectFragment)
            add(squareFragment)
            add(publicNumberFragment)
            add(mineFragment)
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        //初始化viewpager2
        vpHome.initFragment(this, fragmentList).run {
            //全部缓存,避免切换回重新加载
            offscreenPageLimit = fragmentList.size
        }
        //取消viewPager2滑动
        vpHome.isUserInputEnabled = false
        vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNav.menu.getItem(position).isChecked = true
            }
        })
        //初始化底部导航栏
        btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> vpHome.setCurrentItem(0, false)
                    R.id.navigation_project -> vpHome.setCurrentItem(1, false)
                    R.id.navigation_navigation -> vpHome.setCurrentItem(2, false)
                    R.id.navigation_wx -> vpHome.setCurrentItem(3, false)
                    R.id.navigation_mine -> vpHome.setCurrentItem(4, false)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }



    override fun getLayoutId() = R.layout.fragment_main

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }


}
