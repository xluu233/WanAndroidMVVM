package com.xlu.wanandroidmvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xlu.kotlinandretrofit.bean.Userbean

/**
 * @Author xlu
 * @Date 2020/8/12 18:24
 * @Description TODO
 */

@Dao
interface UserBeanDao {
    @Query("Select * from UserBean")
    fun getAll():MutableList<Userbean>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:Userbean)
}