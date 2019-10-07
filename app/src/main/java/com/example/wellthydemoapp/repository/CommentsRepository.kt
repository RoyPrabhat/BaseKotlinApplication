package com.example.wellthydemoapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.wellthydemoapp.api.CommentsApiService
import com.example.wellthydemoapp.api.ProductApiClient
import com.example.wellthydemoapp.api.ProductApiService
import com.example.wellthydemoapp.datamodel.Comment
import com.example.wellthydemoapp.datamodel.CommentsListResponse
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.datamodel.ProductListResponse
import com.example.wellthydemoapp.db.ProductDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommentsRepository {

    private var comments: MutableLiveData<ArrayList<Comment>>? = null
    private lateinit var mCommentApiService: CommentsApiService

    fun getComments(productId: String, pageNumber: String): MutableLiveData<ArrayList<Comment>> {

        mCommentApiService = ProductApiClient.createService(CommentsApiService::class.java)

        if (comments == null)
            comments = MutableLiveData()


        mCommentApiService.getCommentsForProduct(productId, pageNumber)
                .enqueue(object : Callback<CommentsListResponse> {
                    override fun onResponse(call: Call<CommentsListResponse>, response: Response<CommentsListResponse>) {
                        if (response.isSuccessful) {
                            comments!!.value = response.body()!!.posts
                        }
                    }

                    override fun onFailure(call: Call<CommentsListResponse>, t: Throwable) {
                        comments!!.value = null
                    }

                })

        return comments!!
    }

}