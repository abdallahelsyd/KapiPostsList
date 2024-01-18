package com.example.kapipostslist.domain.repository

import com.example.kapipostslist.data.remote.PostsResponse

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
interface PostsRepository {
    suspend fun getPosts():PostsResponse
}