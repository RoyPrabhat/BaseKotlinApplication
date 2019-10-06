package com.example.wellthydemoapp.di

import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.view.prodlist.ProductListFragment
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, APIModule::class, ViewModelModule::class])
interface ApplicationComponent {
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: MyApplication): Builder
//
//        fun build(): ApplicationComponent
//    }

    fun inject(productListFragment: ProductListFragment)
    //   void inject(BreedListFragment breedListFragment);

}