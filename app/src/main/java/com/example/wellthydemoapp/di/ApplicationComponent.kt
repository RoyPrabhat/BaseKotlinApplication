package com.example.wellthydemoapp.di

import com.example.wellthydemoapp.view.comment.CommentListFragment
import com.example.wellthydemoapp.view.prodlist.ProductListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(productListFragment: ProductListFragment)
    fun inject(commentListFragment: CommentListFragment)

}