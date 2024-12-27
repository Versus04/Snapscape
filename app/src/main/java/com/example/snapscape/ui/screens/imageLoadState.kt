package com.example.snapscape.ui.screens

import androidx.annotation.DrawableRes
import com.example.snapscape.model.Urls

sealed class ImageState
{
    object Loading : ImageState()
    data class Success(val urllist : List<Urls>) : ImageState()
   object Error : ImageState()
}