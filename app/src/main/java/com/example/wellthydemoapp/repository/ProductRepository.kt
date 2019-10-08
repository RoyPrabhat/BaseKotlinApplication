package com.example.wellthydemoapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.wellthydemoapp.api.ProductApiClient
import com.example.wellthydemoapp.api.ProductApiService
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.datamodel.ProductListResponse
import com.example.wellthydemoapp.db.InsertTask
import com.example.wellthydemoapp.db.ProductDB
import com.example.wellthydemoapp.db.RetrieveTask
import com.example.wellthydemoapp.db.TaskCompleted
import com.example.wellthydemoapp.util.ConnectivityUtil
import com.example.wellthydemoapp.util.DataFilterUtility
import com.example.wellthydemoapp.util.DateUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ProductRepository @Inject constructor(val productDB: ProductDB) : TaskCompleted {

    private var productList: MutableLiveData<ArrayList<Post>>? = null
    private lateinit var mProductApiService: ProductApiService

    fun getProductByDate(date: String, applicationContext: Context): MutableLiveData<ArrayList<Post>> {

        mProductApiService = ProductApiClient.createService(ProductApiService::class.java)

        if (productList == null)
            productList = MutableLiveData()

        if (ConnectivityUtil.isNetworkConnected(applicationContext)) {
            mProductApiService.getProductByDate(date)
                .enqueue(object : Callback<ProductListResponse> {

                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if (response.isSuccessful) {
                            productList!!.value = response.body()!!.posts
                            if (date == DateUtil.currentDate) {
                                InsertTask(productDB.productDao(), productList!!.value!!).execute()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        productList!!.value = null
                    }

                })
        } else {
            if (date == DateUtil.currentDate) {
                RetrieveTask(productDB.productDao(), this).execute()
            }

        }

        return productList!!
    }

    override fun onTaskComplete(result: List<Post>) {
        productList!!.value = result?.let { ArrayList(it) }
    }

    fun getFilteredProduct(name: String = "", tagLine: String = ""): MutableLiveData<ArrayList<Post>> {
        if (productList == null)
            productList = MutableLiveData()

        if (productList?.value != null) {
            if (productList?.value?.size != 0) {
                if (name == "" && tagLine != "") {
                    productList!!.value = DataFilterUtility.filterDataByTagline(productList!!.value!!, tagLine)
                } else if (name != "" && tagLine == "") {
                    productList!!.value = DataFilterUtility.filterDataByName(productList!!.value!!, name)
                } else {
                    productList!!.value = DataFilterUtility.filterData(productList!!.value!!, name, tagLine)
                }
            } else {
                productList!!.value = null
            }
        } else {
            productList!!.value = null
        }

        return productList!!
    }

}
