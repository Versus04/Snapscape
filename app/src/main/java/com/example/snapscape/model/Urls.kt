package com.example.snapscape.model

import kotlinx.serialization.Serializable


data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)