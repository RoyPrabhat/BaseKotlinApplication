package com.example.wellthydemoapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.wellthydemoapp.api.ProductApiClient
import com.example.wellthydemoapp.api.ProductApiService
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.datamodel.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private var productList: MutableLiveData<ArrayList<Post>>? = null
    private lateinit var mProductApiService: ProductApiService

    fun getProductByDate(date: String): MutableLiveData<ArrayList<Post>> {

        if (productList != null && productList!!.value != null) {
            return productList!!
        } else {
            mProductApiService = ProductApiClient.createService(ProductApiService::class.java)

            productList = MutableLiveData()

            mProductApiService.getProductByDate("2019-10-05")
                .enqueue(object : Callback<ProductListResponse> {
                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if(response.isSuccessful){
                            productList!!.value = response.body()!!.posts
                        }
                    }

                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        productList!!.value = null
                    }

                })

            return productList!!
        }
    }

}
