package com.example.kapipostslist.presentaion.postDetails

import com.example.kapipostslist.domain.models.PostItem

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
data class PostDetailsViewState(
    val isLoading: Boolean = false,
    val post: PostItem? = null,
    val error: String = ""
)
