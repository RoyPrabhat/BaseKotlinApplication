package com.example.wellthydemoapp.api

import com.example.wellthydemoapp.datamodel.CommentsListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CommentsApiService {

    @Headers("Authorization: Bearer HFnfZwx7WNgri2jvs-dSYDDRxUA-OTKl8qqtVATODJI")
    @GET("comments?per_page=5")
    fun getCommentsForProduct(
        @Query("search[post_id]") productId: String,
        @Query("page") pageNumber: String
    ): Call<CommentsListResponse>

}