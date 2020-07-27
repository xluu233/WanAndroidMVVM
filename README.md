# WanAndroid-MVVM-kotlin

## 简介：
此版本WanAndroid采用MVVM架构设计，kotlin语言编写，是对jetpack组件和主流第三方框架的一个实践。后续会不断维护改进，有问题请提交[issues](https://github.com/IMAlex233/WanAndroidMVVM/issues)

采用的主流开发库：

```
    //navigation
    api 'androidx.navigation:navigation-fragment:2.3.0'
    api 'androidx.navigation:navigation-ui:2.3.0'
    api 'androidx.navigation:navigation-fragment-ktx:2.3.0'
    api 'androidx.navigation:navigation-ui-ktx:2.3.0'
    //lifecycle
    api 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    api "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    api "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha05"
    //Kotlin协程核心库
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
    // liveData
    api 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha05'
    // viewModel
    api 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha05'
    api 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    //glide
    api 'com.github.bumptech.glide:glide:4.11.0'
    //rxjava
    api 'io.reactivex.rxjava2:rxjava:2.2.14'
    //rxandroid
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'
    //okhttp
    api 'com.squareup.okhttp3:okhttp:3.12.3'
    api 'com.squareup.okhttp3:logging-interceptor:3.12.3'
    // Retrofit
    api 'com.squareup.retrofit2:retrofit:2.9.0'
    api 'com.squareup.retrofit2:converter-scalars:2.9.0'
    api 'com.squareup.retrofit2:converter-gson:2.9.0'
    api 'com.squareup.retrofit2:adapter-rxjava2:2.7.2'
    //cookie持久化
    api 'com.github.franmontiel:PersistentCookieJar:v1.0.1'
    //eventBus
    api 'org.greenrobot:eventbus:3.2.0'
    //smartFreshLayout
    api "com.scwang.smartrefresh:SmartRefreshLayout:1.1.2"
    api "com.scwang.smartrefresh:SmartRefreshHeader:1.1.2"
    //tablayout
    api 'com.flyco.tablayout:FlycoTabLayout_Lib:3.0.0'
    //banner
    api 'com.youth.banner:banner:2.1.0'
    //recyclerView
    api 'androidx.recyclerview:recyclerview:1.1.0'
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4'
    //viewPager
    api 'androidx.viewpager2:viewpager2:1.0.0'
    api 'androidx.viewpager:viewpager:1.0.0'
    //cardView
    api 'androidx.cardview:cardview:1.0.0'
    //likeButton
    api 'com.github.jd-alexander:LikeButton:0.2.3'
    //search框记录
    api 'com.github.donkingliang:LabelsView:1.6.1'
    //flexbox
    api 'com.google.android:flexbox:2.0.1'
```

 
## 项目：

<!--![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-53-59-623_com.xlu.wanand.jpg)![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-04-519_com.xlu.wanand.jpg)![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-07-063_com.xlu.wanand.jpg)

![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-11-475_com.xlu.wanand.jpg)![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-13-749_com.xlu.wanand.jpg)![描述](https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-28-00-35-27-275_com.xlu.wanand.jpg)-->


<table>
    <tr>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-53-59-623_com.xlu.wanand.jpg">nav1</center></td>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-04-519_com.xlu.wanand.jpg">nav2</center></td>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-07-063_com.xlu.wanand.jpg">nav2</center></td>
    </tr>
</table>

<table>
    <tr>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-11-475_com.xlu.wanand.jpg">nav1</center></td>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-27-23-54-13-749_com.xlu.wanand.jpg">nav2</center></td>
        <td ><center><img src="https://github.com/IMAlex233/WanAndroidMVVM/blob/master/image/Screenshot_2020-07-28-00-35-27-275_com.xlu.wanand.jpg">nav2</center></td>
    </tr>
</table>