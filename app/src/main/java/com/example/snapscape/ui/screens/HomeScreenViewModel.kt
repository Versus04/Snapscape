package com.example.snapscape.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.model.Urls
import com.example.snapscape.network.api
import com.example.snapscape.network.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val repository = UnsplashRepository(api.retrofitservice)
    // This will hold our paging data
    val photos: Flow<PagingData<UnsplashImageDTO>> = repository
        .getPhotosStream()
        .cachedIn(viewModelScope)
}