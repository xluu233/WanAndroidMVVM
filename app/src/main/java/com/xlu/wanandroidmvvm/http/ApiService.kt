package com.xlu.wanandroidmvvm.http

import com.xlu.kotlinandretrofit.bean.*
import com.xlu.kotlinandretrofit.bean.Collection
import retrofit2.http.*

interface ApiService {
    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    //suspend fun getAccountTabList(): ApiResponse<MutableList<Wx.WxList>>
    suspend fun getAccountTabList(): test


    /**
     * 获取公众号历史数据
     * https://wanandroid.com/wxarticle/list/408/1/json
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    suspend fun getAccountList(@Path("id")cid:Int,@Path("pageNum")pageNum:Int): ApiResponse<Wx.WxArticle>

    /*
    * 公众号中搜索历史文章
    * https://wanandroid.com/wxarticle/list/405/1/json?k=Java
    */
    //@GET("/wxarticle/list/{id}/{page}/json?k=Java")
    //suspend fun searchWxArticle(@Path("id")cid: Int,@Path("page") pageNum: Int,@Path("k")filed:String) : ApiResponse<>

    /*
    * 获取开源项目
    */
    @GET("/article/listproject/{page}/json")
    suspend fun getOpenProjects(@Path("page")pageNum: Int) : ApiResponse<OpenProject>


    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeList(@Path("page") pageNo: Int): ApiResponse<Article>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    suspend fun getTopList(): ApiResponse<MutableList<TopArticle>>

    /**
     * banner
     */
    @GET("/banner/json")
    suspend fun getBanner(): ApiResponse<MutableList<Banner>>

    /*常用网站*/
    @GET("/friend/json")
    suspend fun getFriendUrl() : ApiResponse<FriendUrl>

    /*搜索热词：目前搜索热门*/
    @GET("/hotkey/json")
    suspend fun getHotkey() : ApiResponse<HotKey>

    /**
     * 体系
     */
    @GET("/tree/json")
    suspend fun getSystemList() : ApiResponse<MutableList<SystemBean>>

    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{pageNum}/json")
    suspend fun getSystemArticle(@Path("pageNum")pageNum:Int,@Query("cid")cid:Int) : ApiResponse<Article>

    /*按照作者昵称搜索文章*/
    @GET("/article/list/{page}/json?author={author}")
    suspend fun searchArticleFromAuthor(@Path("page")pageNum: Int,@Path("author")author:String) : ApiResponse<Article>

    /**
     * 导航数据
     */
    @GET("/navi/json")
    fun getNavigation() : ApiResponse<MutableList<NavigationEntity>>

    /*项目分类*/
    @GET("/project/tree/json")
    suspend fun getProjectClassification() : ApiResponse<ProjectClassification>

    /*项目分类下的数据，cid为项目id*/
    @GET("/project/list/{page}/json?cid={id}")
    suspend fun getProjectClassificationList(@Path("page")pageNum: Int,@Path("id")cid:Int) : ApiResponse<Article>


    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): ApiResponse<Userbean>
    //退出登录
    @GET("/user/logout/json")
    suspend fun logout():ApiResponse<Any>

    /**
     * 注册
     */
    @POST("/user/register")
    suspend fun register(@Query("username")username: String,
                         @Query("password")password: String,
                         @Query("repassword")repassword: String) : ApiResponse<Any>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNum: Int): ApiResponse<Collection>

    /**
     * 收藏站内文章
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collect(@Path("id")id:Int):ApiResponse<Any>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): ApiResponse<Any>

    /*收藏站外文章*/
    @POST("/lg/collect/add/json")
    suspend fun collectOtherArticle(@Field("title") title: String,@Field("author") author: String,@Field("link")link: String) : ApiResponse<Any>

    /*搜索*/
    @POST("/article/query/{page}/json")
    suspend fun getSearch(@Path("page")pageNum: Int,@Field("k")value:String):ApiResponse<Article>

    /*积分排行榜*/
    @GET("https://www.wanandroid.com/coin/rank/{page}/json")
    suspend fun getCoinRank(@Path("page") pageNum: Int = 0) : ApiResponse<CoinRank>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun getCoin():ApiResponse<Coin>

    /*积分获取的详细列表*/
    @GET("/lg/coin/list/{page}/json")
    suspend fun getCoinList(@Path("page")pageNum: Int = 0) : ApiResponse<CoinList>

    /*获取广场文章数据*/
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticle(@Path("page")pageNum: Int = 0) : ApiResponse<Article>

    /*作者分享的文章*/
    @GET("/user/{id}/share_articles/{page}/json")
    suspend fun getAuthorList(@Path("id")id: Int,@Path("page")pageNum: Int  = 1):ApiResponse<ShareArticle>

    /*自己分享的文章*/
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun getMyArticleList(@Path("page")pageNum: Int = 1) :ApiResponse<ShareArticle>

    /*删除分享文章*/
    @POST("/lg/user_article/delete/{id}/json")
    suspend fun deleteShareArticle(@Path("id")id: Int) : ApiResponse<Any>

    /*分享文章*/
    @POST("/lg/user_article/add/json")
    fun shareArticle(@Query("title")title: String,@Query("link")link: String): ApiResponse<Any>


}