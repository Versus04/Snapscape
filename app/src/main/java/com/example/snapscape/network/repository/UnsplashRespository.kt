package com.example.snapscape.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.network.ApiService
import com.example.snapscape.paging.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow

class UnsplashRepository(
    private val apiService: ApiService
) {
    fun getPhotosStream(): Flow<PagingData<UnsplashImageDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { UnsplashPagingSource(apiService) }
        ).flow
    }
}