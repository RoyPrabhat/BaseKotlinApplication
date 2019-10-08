package com.example.wellthydemoapp.di

import androidx.room.Room
import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.db.ProductDB
import com.example.wellthydemoapp.repository.CommentsRepository
import com.example.wellthydemoapp.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    internal fun provideMyApplication(): MyApplication {
        return application
    }

    @Provides
    internal fun providesProductRepository(productDB : ProductDB): ProductRepository {
        return ProductRepository(productDB)
    }

    @Provides
    internal fun providesCommentRepository(): CommentsRepository {
        return CommentsRepository()
    }

    @Provides
    @Inject
    internal fun providesProductDB(application: MyApplication): ProductDB{
        return Room.databaseBuilder(
            application,
            ProductDB::class.java, "product-list.db"
        ).build()
    }
}