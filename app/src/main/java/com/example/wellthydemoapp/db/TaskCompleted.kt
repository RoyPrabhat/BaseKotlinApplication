package com.example.wellthydemoapp.db

import com.example.wellthydemoapp.datamodel.Post

interface TaskCompleted {
    fun onTaskComplete(result: List<Post>)
}
