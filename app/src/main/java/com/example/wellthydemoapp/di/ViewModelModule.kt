package com.example.wellthydemoapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.wellthydemoapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}