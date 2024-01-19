package com.example.kapipostslist.presentaion.postsList

import androidx.paging.PagingData
import com.example.kapipostslist.data.remote.PostsResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
class PostsListViewState (
    val isLoading:Boolean=false,
    val posts: PostsResponse?=null,
    val error:String=""
)