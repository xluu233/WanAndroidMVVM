package com.xlu.kotlinandretrofit.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//登录-用户数据

@Entity
data class Userbean(
    val admin: Boolean,
    val chapterTops: List<Any>,

    val collectIds: List<Int>,

    val email: String,
    val icon: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val nickname: String,

    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)