package com.xlu.wanandroidmvvm

import android.content.Intent
import android.os.Bundle
import com.xlu.base_library.base.BaseActivity
import com.xlu.base_library.utils.PrefUtils
import com.xlu.base_library.utils.StatusUtils
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity() {

    lateinit var timer: Timer

    override fun getLayoutId(): Int?  = R.layout.activity_login

    override fun init(savedInstanceState: Bundle?) {
        changeTheme()
        startIntent()
    }

    /**
     * 开始倒计时跳转
     */
    private fun startIntent(){

        Timer("SettingUp", false).schedule(2000) {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }

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

    override fun onDestroy() {
        super.onDestroy()
    }

}

