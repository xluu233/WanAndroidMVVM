package com.xlu.kotlinandretrofit.bean

data class CoinList(
    val curPage: Int,
    val datas: List<Data>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
) {
    data class Data(
        val coinCount: Int,
        val date: Long,
        val desc: String,
        val id: Int,
        val reason: String,
        val type: Int,
        val userId: Int,
        val userName: String
    )
}