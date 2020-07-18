package com.xlu.kotlinandretrofit.bean

data class ShareArticle(
    val coinInfo: CoinInfo,
    val shareArticles: ShareArticles
) {
    //分享者的积分信息
    data class CoinInfo(
        val coinCount: Int,
        val level: Int,
        val rank: String,
        val userId: Int,
        val username: String
    )

    //分享者的分享列表
    data class ShareArticles(
        val curPage: Int,
        val datas: List<Data>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    ) {
        data class Data(
            val apkLink: String,
            val audit: Int,
            val author: String,
            val canEdit: Boolean,
            val chapterId: Int,
            val chapterName: String,
            val collect: Boolean,
            val courseId: Int,
            val desc: String,
            val descMd: String,
            val envelopePic: String,
            val fresh: Boolean,
            val id: Int,
            val link: String,
            val niceDate: String,
            val niceShareDate: String,
            val origin: String,
            val prefix: String,
            val projectLink: String,
            val publishTime: Long,
            val realSuperChapterId: Int,
            val selfVisible: Int,
            val shareDate: Long,
            val shareUser: String,
            val superChapterId: Int,
            val superChapterName: String,
            val tags: List<Tag>,
            val title: String,
            val type: Int,
            val userId: Int,
            val visible: Int,
            val zan: Int
        ) {
            data class Tag(
                val name: String,
                val url: String
            )
        }
    }
}