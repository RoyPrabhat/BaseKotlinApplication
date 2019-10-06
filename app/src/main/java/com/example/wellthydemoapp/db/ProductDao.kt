package com.example.wellthydemoapp.db

import androidx.room.*
import com.example.wellthydemoapp.datamodel.Post

@Dao
interface ProductDao {

    @Query("SELECT * FROM post_list")
    fun getAll(): ArrayList<Post>

    @Query("SELECT * FROM post_list WHERE day LIKE :date")
    fun findByDate(date: String): ArrayList<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: ArrayList<Post>)

//    @Delete
////    fun delete(todo: TodoEntity)
}