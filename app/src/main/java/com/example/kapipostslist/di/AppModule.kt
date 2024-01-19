package com.example.kapipostslist.di

import com.example.kapipostslist.common.Constants
import com.example.kapipostslist.data.remote.PostsAPI
import com.example.kapipostslist.data.repository.PostsRepositoryImp
import com.example.kapipostslist.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePostAPI(): PostsAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsAPI::class.java)
    }
    @Provides
    @Singleton
    fun providePostRepository(api:PostsAPI): PostsRepository {
        return PostsRepositoryImp(api)
    }
}