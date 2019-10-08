package com.example.wellthydemoapp.db

import android.os.AsyncTask
import com.example.wellthydemoapp.datamodel.Post

class InsertTask(
    private val mDao: ProductDao, private val posts: ArrayList<Post>
) : AsyncTask<Void, Void, Int>() {

    override fun doInBackground(vararg params: Void): Int? {
        mDao.insertAll(posts)
        return mDao.getAll().size
    }
}