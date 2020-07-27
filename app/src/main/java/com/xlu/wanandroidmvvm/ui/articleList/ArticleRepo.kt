package com.xlu.wanandroidmvvm.ui.articleList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.http.ApiException
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.wanandroidmvvm.Constants
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * @Author xlu
 * @Date 2020/7/22 23:18
 * @Description TODO
 */
class ArticleRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    private var page = 1;
    fun getProjectList(
        type: Int,
        tabId: Int,
        refresh: Boolean =false,
        projectLiveData: MutableLiveData<MutableList<Article.Data>>
    ){
        launch(
            block = {
                if (refresh) page =1 else page++

                if (type == Constants.PROJECT_TYPE) {
                    //项目
                    RetrofitManager.getApiService(ApiService::class.java)
                        //.getProjectList(page,tabId)
                        .getProjectClassificationList(page,tabId)
                        .data()
                }else{
                    //微信公总号
                    RetrofitManager.getApiService(ApiService::class.java)
                        .getAccountList(tabId,page)
                        .data()
                }

            },
            success = {
                projectLiveData.postValue(it.datas.toMutableList())
/*                for (item in it.datas){
                    Log.d("position-repo:",item.desc)
                }*/
/*                projectLiveData.value.apply {
                    //第一次加载 或 刷新 给 articleLiveData 赋予一个空集合
                    val currentList = if (refresh || this == null){
                        mutableListOf()
                    }else{
                        this
                    }
                    it.datas?.let { it1 -> currentList.addAll(it1) }
                    projectLiveData.postValue(currentList)
                }*/

            }
        )
    }

    /**
     * 收藏
     */
    fun collect(id:Int,collectLiveData : MutableLiveData<Int>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .collect(id)
                    .data(Any::class.java)
            },
            success = {
                collectLiveData.postValue(id)
            }
        )
    }



    /**
     * 取消收藏
     */
    fun unCollect(id:Int,unCollectLiveData : MutableLiveData<Int>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .unCollect(id)
                    //如果data可能为空,可通过此方式通过反射生成对象,避免空判断
                    .data(Any::class.java)
            },
            success = {
                unCollectLiveData.postValue(id)
            }
        )
    }



}