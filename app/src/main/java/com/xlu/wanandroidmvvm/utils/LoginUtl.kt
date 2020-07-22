package com.xlu.wanandroidmvvm.utils

import com.xlu.base_library.utils.PrefUtils
import com.xlu.wanandroidmvvm.Constants
import org.greenrobot.eventbus.EventBus

/**
 * @Author xlu
 * @Date 2020/7/22 17:16
 * @Description TODO
 */
class LoginUtl {
    companion object{

        /**
         * 登录状态
         */
        fun isLogin():Boolean{
            return PrefUtils.getBoolean(Constants.LOGIN, false)
        }

        /**
         * 退出登录，重置用户状态
         */
        fun resetUser() {
            //发送退出登录消息
            //EventBus.getDefault().post(LogoutEvent())
            //更新登陆状态
            PrefUtils.setBoolean(Constants.LOGIN, false)
            //移除用户信息
            PrefUtils.removeKey(Constants.USER_INFO)
            //移除积分信息
            PrefUtils.removeKey(Constants.INTEGRAL_INFO)
        }


    }
}