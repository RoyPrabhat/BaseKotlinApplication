package com.example.wellthydemoapp.api

import com.example.wellthydemoapp.datamodel.ProductListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @Headers("Authorization: Bearer HFnfZwx7WNgri2jvs-dSYDDRxUA-OTKl8qqtVATODJI")
    @GET("posts")
    fun getProductByDate(@Query("day") date: String): Call<ProductListResponse>
}
