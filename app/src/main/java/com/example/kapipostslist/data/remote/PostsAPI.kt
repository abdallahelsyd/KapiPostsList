package com.example.kapipostslist.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
interface PostsAPI {
    @GET("posts")
    suspend fun getPosts():PostsResponse
}