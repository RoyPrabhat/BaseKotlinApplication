package com.example.wellthydemoapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.api.CommentsApiService
import com.example.wellthydemoapp.api.ProductApiClient
import com.example.wellthydemoapp.datamodel.Comment
import com.example.wellthydemoapp.datamodel.CommentsListResponse
import com.example.wellthydemoapp.util.ConnectivityUtil
import com.example.wellthydemoapp.util.ToastUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRepository {

    private var comments: MutableLiveData<ArrayList<Comment>>? = null
    private lateinit var mCommentApiService: CommentsApiService

    fun getComments(productId: String, pageNumber: String, applicationContext: Context): MutableLiveData<ArrayList<Comment>> {

        mCommentApiService = ProductApiClient.createService(CommentsApiService::class.java)

        if (comments == null)
            comments = MutableLiveData()

        if (ConnectivityUtil.isNetworkConnected(applicationContext)) {
        mCommentApiService.getCommentsForProduct(productId, pageNumber)
            .enqueue(object : Callback<CommentsListResponse> {

                override fun onResponse(call: Call<CommentsListResponse>, response: Response<CommentsListResponse>) {
                    if (response.isSuccessful) {
                        comments!!.value = response.body()!!.comments
                    }
                }

                override fun onFailure(call: Call<CommentsListResponse>, t: Throwable) {
                    comments!!.value = null
                }

            }) } else{
            ToastUtil.showToast(applicationContext, applicationContext.getString(R.string.no_internet))
        }

        return comments!!
    }

}
