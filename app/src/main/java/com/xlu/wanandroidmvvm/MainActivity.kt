package com.xlu.wanandroidmvvm

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.xlu.base_library.base.BaseActivity
import com.xlu.base_library.utils.PrefUtils
import com.xlu.base_library.utils.StatusUtils
import com.xlu.wanandroidmvvm.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {


    override fun init(savedInstanceState: Bundle?) {
        changeTheme()
    }

    override fun getLayoutId(): Int? {
        return R.layout.activity_main
    }

    /**
     * 动态切换主题
     */
    private fun changeTheme() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            setTheme(R.style.AppTheme_Night)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    /**
     * 沉浸式状态,随主题改变
     */
    override fun setSystemInvadeBlack() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            StatusUtils.setSystemStatus(this, true, false)
        } else {
            StatusUtils.setSystemStatus(this, true, true)
        }
    }


}