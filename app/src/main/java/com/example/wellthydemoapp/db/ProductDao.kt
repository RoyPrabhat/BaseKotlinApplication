package com.example.wellthydemoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wellthydemoapp.datamodel.Post

@Dao
interface ProductDao {

    @Query("SELECT * FROM post_list")
    fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: ArrayList<Post>)

}