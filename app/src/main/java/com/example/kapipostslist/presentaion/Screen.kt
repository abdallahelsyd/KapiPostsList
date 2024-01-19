package com.example.kapipostslist.presentaion

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */
sealed class Screen(val route: String) {
    object PostsListScreen: Screen("posts_list_screen")
    object PostDetailScreen: Screen("post_detail_screen")
}