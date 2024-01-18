package com.example.kapipostslist.domain.useCases

import com.example.kapipostslist.common.Resource
import com.example.kapipostslist.data.remote.PostsResponse
import com.example.kapipostslist.di.ContextProvider
import com.example.kapipostslist.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
@Singleton
class GetPostsUseCase @Inject constructor(
    private val contextProvider: ContextProvider,
    private val repository: PostsRepository
) {
    operator fun invoke(): Flow<Resource<PostsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getPosts()
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured "))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. check your internet "))
        }
    }.flowOn(contextProvider.IO)
}