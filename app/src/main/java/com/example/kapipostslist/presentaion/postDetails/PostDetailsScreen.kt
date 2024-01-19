package com.example.kapipostslist.presentaion.postDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kapipostslist.presentaion.PostsViewModel

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */

@Composable
fun PostDetailScreen(
    navController: NavController,
    viewModel: PostsViewModel
) {
    val state=viewModel.detailState.value
    Column(
        Modifier.background(Color.White).fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var title by rememberSaveable { mutableStateOf(state.post?.title?:"") }
        var body by rememberSaveable { mutableStateOf(state.post?.body?:"") }
        state.post?.let { post ->
            TextField(
                title,
                {
                    title = it
                }, label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
            TextField(
                value = body,
                onValueChange = {
                    body = it
                },
                label = { Text("Body") },
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
        }
        Button(
            onClick = {
                state.post?.let { viewModel.editPost(title,body) }
                navController.popBackStack()
            }
        ){
            Text("Save")
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
