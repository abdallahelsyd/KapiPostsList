package com.example.kapipostslist.data.repository

import com.example.kapipostslist.data.remote.PostsAPI
import com.example.kapipostslist.data.remote.PostsResponse
import com.example.kapipostslist.domain.repository.PostsRepository
import javax.inject.Inject

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
class PostsRepositoryImp @Inject constructor(val api:PostsAPI):PostsRepository {
    override suspend fun getPosts(): PostsResponse {
        return api.getPosts()
    }
}