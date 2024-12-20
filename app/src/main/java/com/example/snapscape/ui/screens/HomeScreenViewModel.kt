package com.example.snapscape.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.model.Urls
import com.example.snapscape.network.api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _imageData = MutableStateFlow<List<Urls>>(emptyList())
    val imageData : StateFlow<List<Urls>> = _imageData.asStateFlow()

    private fun getPhotos()
    {
        viewModelScope.launch{
            try {
                val response = api.retrofitservice.getPhotos()
                if (response.isSuccessful) {
                    response.body()?.let { unsplashImages ->
                        _imageData.value = unsplashImages.map { it.urls }
                    }
                } else {
                    Log.e("unreal", "Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("unreal", "Exception: ${e.message}", e)
            }
        }
    }

init {
    getPhotos()
}
}