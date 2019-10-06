package com.example.wellthydemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.repository.ProductRepository
import java.util.ArrayList
import javax.inject.Inject

class ProdListViewModel
@Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    private var mProdList: MutableLiveData<ArrayList<Post>>? = null

    fun getProductList(): MutableLiveData<ArrayList<Post>> {
        return productRepository.getProductByDate("")
    }
}