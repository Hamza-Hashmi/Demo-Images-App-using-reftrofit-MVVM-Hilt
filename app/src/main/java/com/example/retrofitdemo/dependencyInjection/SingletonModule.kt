package com.example.retrofitdemo.dependencyInjection

import com.example.retrofitdemo.adapter.ImagesAdapter
import com.example.retrofitdemo.repository.PicSumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SingletonModule {
    @Provides
    @Singleton
    fun provideImagesAdapter() = ImagesAdapter()

    @Provides
    @Singleton
    fun providePicSumRepository() = PicSumRepository()
}