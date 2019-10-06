package com.example.wellthydemoapp.di

import com.example.wellthydemoapp.view.prodlist.ProductListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, APIModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(productListFragment: ProductListFragment)

}