package com.example.kapipostslist.domain.models

data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)