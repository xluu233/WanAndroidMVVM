package com.xlu.wanandroidmvvm.ui.search

import androidx.lifecycle.MutableLiveData
import com.xlu.base_library.base.BaseRepository
import com.xlu.base_library.common.isListEmpty
import com.xlu.base_library.common.toast
import com.xlu.base_library.http.ApiException
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.wanandroidmvvm.http.ApiService
import com.xlu.wanandroidmvvm.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * @Author xlu
 * @Date 2020/7/26 0:57
 * @Description TODO
 */
class SearchRepo(coroutineScope: CoroutineScope,errorLiveData: MutableLiveData<ApiException>) : BaseRepository(coroutineScope,errorLiveData) {

    private var page = 0

    /**
     * 搜索
     */
    fun search(
        isRefresh: Boolean,
        keyWord: String,
        articleLiveData: MutableLiveData<MutableList<Article.Data>>,
        emptyLiveData: MutableLiveData<Any>
    ) {
        launch(
            block = {
                if (isRefresh) {
                    page = 0
                } else {
                    page++
                }
                RetrofitManager.getApiService(ApiService::class.java)
                    .getSearch(page, keyWord)
                    .data()
            },
            success = {
                //处理刷新/分页数据
                articleLiveData.value.apply {
                    //第一次加载 或 刷新 给 articleLiveData 赋予一个空集合
                    val currentList = if (isRefresh || this == null) {
                        mutableListOf()
                    } else {
                        this
                    }
                    currentList.addAll(it.datas)
                    articleLiveData.postValue(currentList)
                }
                if (isListEmpty(it.datas)) {
                    //第一页并且数据为空
                    if (page == 0) {
                        emptyLiveData.postValue(Any())
                    } else {
                        toast("没有数据啦～")
                    }
                }
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