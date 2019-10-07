package com.example.wellthydemoapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.wellthydemoapp.api.ProductApiClient
import com.example.wellthydemoapp.api.ProductApiService
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.datamodel.ProductListResponse
import com.example.wellthydemoapp.db.ProductDB
import com.example.wellthydemoapp.util.ConnectivityUtil
import com.example.wellthydemoapp.util.DateUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import android.os.AsyncTask
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class ProductRepository @Inject constructor(val productDB: ProductDB)  {

    private var productList: MutableLiveData<ArrayList<Post>>? = null
    private lateinit var mProductApiService: ProductApiService

    fun getProductByDate(date: String, applicationContext: Context): MutableLiveData<ArrayList<Post>> {

        mProductApiService = ProductApiClient.createService(ProductApiService::class.java)

        if (productList == null)
            productList = MutableLiveData()

        if(ConnectivityUtil.isNetworkConnected(applicationContext)) {
            mProductApiService.getProductByDate(date)
                .enqueue(object : Callback<ProductListResponse> {
                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if (response.isSuccessful) {
                            productList!!.value = response.body()!!.posts
                            if(date == DateUtil.currentDate ){

                                val myExecutor = Executors.newSingleThreadExecutor()
                                myExecutor.execute({
                                    response.body()!!.posts?.let {
                                        productDB.productDao().insertAll(it)
                                    }
                                })

//                                response.body()!!.posts?.let { productDB.productDao().insertAll(it) }
//
//                                object : AsyncTask<ArrayList<Post>, Void, Void>() {
//                                    override fun doInBackground(vararg posts: ArrayList<Post>): Void? {
//                                        productDB.productDao().insertAll(posts)
//                                        return null
//                                    }
//                                }.execute()

                            }
                        }
                    }

                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        productList!!.value = null
                    }

                })
        } else {

            var roomData : ArrayList<Post>? = null

            val myExecutor = Executors.newSingleThreadExecutor()
            myExecutor.execute {
                roomData  =  ArrayList(productDB.productDao().getAll())

            }

            productList!!.value = roomData
            }

        return productList!!
    }

}
