package com.example.kapipostslist.presentaion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kapipostslist.common.Resource
import com.example.kapipostslist.di.ContextProvider
import com.example.kapipostslist.domain.useCases.GetPostsUseCase
import com.example.kapipostslist.presentaion.postsList.PostsListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val contextProvider: ContextProvider
):ViewModel(){
    private val _state= mutableStateOf(PostsListViewState())
    val state: State<PostsListViewState> =_state

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch(contextProvider.Main) {
            getPostsUseCase.invoke().onEach {
                when(it){
                    is Resource.Error -> _state.value= PostsListViewState(error = it.message ?: "An unexpected error occored")
                    is Resource.Loading -> _state.value = PostsListViewState(isLoading = true)
                    is Resource.Success -> _state.value =
                        PostsListViewState(posts = it.data)
                }
            }.launchIn(viewModelScope)
        }
    }


}