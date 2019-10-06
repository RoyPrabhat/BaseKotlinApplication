package com.example.wellthydemoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wellthydemoapp.datamodel.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class ProductDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
}