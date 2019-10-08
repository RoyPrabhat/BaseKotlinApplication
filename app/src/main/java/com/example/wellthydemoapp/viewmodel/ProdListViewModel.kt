package com.example.wellthydemoapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.repository.ProductRepository
import java.util.*
import javax.inject.Inject

class ProdListViewModel
@Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    lateinit var date : String

    fun getProductList(date: String, applicationContext: Context): LiveData<ArrayList<Post>> {
        if( date == ""){
            return productRepository.getProductByDate(this.date, applicationContext)
        } else {
            this.date = date
            return productRepository.getProductByDate(date, applicationContext)
        }

    }

    fun getFilteredProduct(name: String, tagLine: String): LiveData<ArrayList<Post>> {
        return productRepository.getFilteredProduct(name, tagLine)
    }

}