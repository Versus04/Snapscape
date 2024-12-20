package com.example.snapscape.model

import kotlinx.serialization.Serializable


data class UnsplashImageDTO(

    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val urls: Urls,
    val user: User,
    val width: Int
)