package com.xlu.wanandroidmvvm.dao

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xlu.wanandroidmvvm.App

/**
 * @Author xlu
 * @Date 2020/8/12 18:49
 * @Description TODO
 */
object DBInstance {
    private const val DB_NAME = "test.db"
    private val instance:DataBase by lazy {
        Room.databaseBuilder(App.instance,DataBase::class.java, DB_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.e("db", "create")

                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.e("db", "open")
                }
            })
            .allowMainThreadQueries()
            .build()
    }

    val dao = instance.Userbean()
}