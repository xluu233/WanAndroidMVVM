package com.xlu.kotlinandretrofit.bean

/**
 * @Author xlu
 * @Date 2020/7/14 17:55
 * @Description TODO
 */
class Wx {

    //微信公总号列表
    class WxList{
        val children : List<Any>? = null
        val courseId: Int = 0
        val id: Int = 0
        val name: String? = null
        val order: Int = 0
        val parentChapterId: Int = 0
        val userControlSetTop: Boolean? = null
        val visible: Int = 0
    }

    //微信文章列表
    class WxArticle{
        val curPage: Int = 0
        val datas: List<Datas>? = null
        val offset: Int = 0
        val over: Boolean? = null
        val pageCount: Int = 0
        val size: Int = 0
        val total: Int = 0

        class Datas{

            val apkLink: String? = null
            val audit: Int = 0
            val author: String? = null
            val canEdit: Boolean? = null
            val chapterId: Int = 0
            val chapterName: String? = null
            val collect: Boolean? = null
            val courseId: Int = 0
            val desc: String? = null
            val descMd: String? = null
            val envelopePic: String? = null
            val fresh: Boolean? = null
            val id: Int = 0
            val link: String? = null
            val niceDate: String? = null
            val niceShareDate: String? = null
            val origin: String? = null
            val prefix: String? = null
            val projectLink: String? = null
            val publishTime: Long? = null
            val realSuperChapterId: Int = 0
            val selfVisible: Int = 0
            val shareDate: Long? = null
            val shareUser: String? = null
            val superChapterId: Int = 0
            val superChapterName: String? = null
            val tags: List<Tag>? = null
            val title: String? = null
            val type: Int? = 0
            val userId: Int = 0
            val visible: Int = 0
            val zan: Int = 0

            class Tag{
                val name: String? = null
                val url: String? = null
            }
        }
    }
}