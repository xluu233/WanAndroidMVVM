package com.xlu.kotlinandretrofit.bean

//常用网站
data class FriendUrl(
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)