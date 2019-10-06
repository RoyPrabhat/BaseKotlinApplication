package com.example.wellthydemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wellthydemoapp.repository.ProductRepository

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject
constructor(
//     private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>,
     val mProductRepository: ProductRepository
) : ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        var creator: Provider<out ViewModel>? = creators[modelClass]
//        if (creator == null) {
//            for ((key, value) in creators) {
//                if (modelClass.isAssignableFrom(key)) {
//                    creator = value
//                    break
//                }
//            }
//        }
//        if (creator == null) {
//            throw IllegalArgumentException("unknown model class $modelClass")
//        }
//        try {
//            return creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ProdListViewModel::class.java -> ProdListViewModel(mProductRepository)
            else -> TODO("Missing viewModel $modelClass")
        } as T
    }

}