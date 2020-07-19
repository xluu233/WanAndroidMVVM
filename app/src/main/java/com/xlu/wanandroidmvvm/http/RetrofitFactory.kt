package com.xlu.wanandroidmvvm.http

import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.xlu.base_library.BaseApp.Companion.getContext
import com.xlu.base_library.http.HttpLoggingInterceptor
import com.xlu.wanandroidmvvm.Constants
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object RetrofitFactory {
    //缓存100Mb
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(
                    Constants.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .connectTimeout(
                    Constants.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .addInterceptor(getLogInterceptor())
                .cookieJar(getCookie())
                .cache(getCache())
        }

    fun factory(): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor(): Interceptor {
        //http log 拦截器
        return HttpLoggingInterceptor("OkHttp").apply {
                setPrintLevel(HttpLoggingInterceptor.Level.BODY)
                setColorLevel(Level.INFO)
            }
    }

    /**
     * 获取cookie持久化
     */
    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(getContext())
        )
    }

    /**
     * 获取缓存方式
     */
    private fun getCache(): Cache {
        //缓存100Mb
        return Cache( File(getContext().cacheDir, "cache")
            , 1024 * 1024 * 100)
    }
}