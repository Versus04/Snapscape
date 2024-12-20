package com.example.snapscape.model

import kotlinx.serialization.Serializable

@Serializable

data class User(
    val bio: String,
    val id: String,
    val instagram_username: String,
    val location: String,
    val name: String,
    val portfolio_url: String,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String,
    val username: String
)