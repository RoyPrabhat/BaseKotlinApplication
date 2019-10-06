package com.example.wellthydemoapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ProductApiClient {

    private const val BASE_URL = "https://api.producthunt.com/v1/"
    private var adapterBuilder: Retrofit.Builder? = null

    private fun createDefaultAdapter() {
        adapterBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun <S> createService(serviceClass: Class<S>): S {
        if (adapterBuilder == null) {
            createDefaultAdapter()
        }
        return adapterBuilder!!.build().create(serviceClass)
    }
}
