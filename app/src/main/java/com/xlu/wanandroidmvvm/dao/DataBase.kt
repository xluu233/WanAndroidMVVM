package com.xlu.wanandroidmvvm.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xlu.kotlinandretrofit.bean.Userbean

/**
 * @Author xlu
 * @Date 2020/8/12 18:45
 * @Description TODO
 */

@Database(entities = [Userbean::class], version = 1)
abstract class DataBase :RoomDatabase() {

    abstract fun Userbean(): UserBeanDao

}