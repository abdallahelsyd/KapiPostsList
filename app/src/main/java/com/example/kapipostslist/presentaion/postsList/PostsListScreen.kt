package com.example.kapipostslist.presentaion.postsList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kapipostslist.domain.models.PostItem
import com.example.kapipostslist.presentaion.PostsViewModel
import com.example.kapipostslist.presentaion.Screen
import com.example.kapipostslist.presentaion.postsList.components.PostItem

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */

@Composable
fun PostsListScreen(
    navController: NavController,
    viewModel: PostsViewModel,
){
    val state=viewModel.state.value
    val listState = rememberLazyListState()

    val postsItemList = viewModel.state.value.posts

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(postsItemList?.size?:0) { item ->
                postsItemList?.get(item)?.let { result ->
                    PostItem (result, onItemClick = {
                        viewModel.setSelectedItem(it)
                        navController.navigate(Screen.PostDetailScreen.route)
                    })
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}