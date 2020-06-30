package com.example.rushabhprajapatipractice.model

data class BlogListItem(
    val comments: Int,
    val content: String,
    val createdAt: String,
    val id: String,
    val likes: Int,
    val media: List<Media>,
    val user: List<User>
)