package com.example.kapipostslist.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kapipostslist.presentaion.postDetails.PostDetailScreen
import com.example.kapipostslist.presentaion.postsList.PostsListScreen
import com.example.kapipostslist.presentaion.ui.theme.KapiPostsListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KapiPostsListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PostsListScreen.route
                    ) {
                        composable(
                            route = Screen.PostsListScreen.route
                        ) {
                            PostsListScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(
                            route = Screen.PostDetailScreen.route
                        ) {
                            PostDetailScreen(navController,viewModel)
                        }
                    }
                }
            }
        }
    }
}



