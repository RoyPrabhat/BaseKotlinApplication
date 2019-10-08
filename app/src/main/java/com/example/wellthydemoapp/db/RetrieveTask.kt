package com.example.wellthydemoapp.db

import android.os.AsyncTask
import com.example.wellthydemoapp.datamodel.Post

class RetrieveTask(
    private val mDao: ProductDao, private val mTaskCompleted: TaskCompleted
) : AsyncTask<Void, List<Post>, List<Post>>() {

    override fun doInBackground(vararg params: Void): List<Post> {
        return mDao.getAll()
    }

    override fun onPostExecute(posts: List<Post>) {
        this.mTaskCompleted.onTaskComplete(posts)
    }
}