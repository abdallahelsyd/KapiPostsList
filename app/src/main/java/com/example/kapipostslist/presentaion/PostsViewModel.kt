package com.example.kapipostslist.presentaion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kapipostslist.common.Resource
import com.example.kapipostslist.di.ContextProvider
import com.example.kapipostslist.domain.models.PostItem
import com.example.kapipostslist.domain.useCases.GetPostsUseCase
import com.example.kapipostslist.presentaion.postDetails.PostDetailsViewState
import com.example.kapipostslist.presentaion.postsList.PostsListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _postsState= mutableStateOf(PostsListViewState())
    val postsState: State<PostsListViewState> =_postsState

    private var currentList= arrayListOf<PostItem>()
    private var currentItem:PostItem?=null
    init {
        getPosts()
    }
    private fun getPosts() {
        viewModelScope.launch(contextProvider.Main) {
            getPostsUseCase.invoke().onEach {
                when(it){
                    is Resource.Error -> _postsState.value= PostsListViewState(error = it.message ?: "An unexpected error occored")
                    is Resource.Loading -> _postsState.value = PostsListViewState(isLoading = true)
                    is Resource.Success -> {
                        _postsState.value = PostsListViewState(posts = it.data)
                        it.data?.let{res->
                            currentList= res
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


    private val _detailState= mutableStateOf(PostDetailsViewState())
    val detailState: State<PostDetailsViewState> =_detailState
    fun setSelectedItem(item: PostItem) {
        _detailState.value= PostDetailsViewState(post = item)
        currentItem=item
    }

    fun editPost(title: String, body: String) {
        val index=currentList.indexOf(currentItem)
        currentItem?.let {
            currentList[index]= it.copy(title=title, body = body)
        }
    }
}