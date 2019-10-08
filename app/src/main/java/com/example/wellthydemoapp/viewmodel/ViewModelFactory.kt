package com.example.wellthydemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wellthydemoapp.repository.CommentsRepository
import com.example.wellthydemoapp.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

class ViewModelFactory @Inject
constructor(
    val mProductRepository: ProductRepository,
    val mCommentsRepository: CommentsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ProdListViewModel::class.java -> ProdListViewModel(mProductRepository)
            CommentListViewModel::class.java -> CommentListViewModel(mCommentsRepository)
            else -> TODO("Missing viewModel $modelClass")
        } as T
    }

}