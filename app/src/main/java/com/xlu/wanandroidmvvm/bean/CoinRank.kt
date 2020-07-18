package com.xlu.kotlinandretrofit.bean

data class CoinRank(
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
        val level: Int,
        val rank: String,
        val userId: Int,
        val username: String
    )
}