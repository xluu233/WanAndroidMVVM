package com.xlu.kotlinandretrofit.bean

//搜索热词
data class HotKey(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)