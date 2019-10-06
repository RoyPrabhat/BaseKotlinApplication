package com.example.wellthydemoapp.di

import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.repository.ProductRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: MyApplication) {

//    @Provides
//    internal fun provideMyApplication(): MyApplication {
//        return application
//    }

    @Provides
    internal fun providesProductRepository(): ProductRepository {
        return ProductRepository()
    }
}