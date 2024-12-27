package com.example.snapscape.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.network.ApiService
import java.io.IOException

class UnsplashPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, UnsplashImageDTO>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashImageDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImageDTO> {
        try {
            val page = params.key ?: 1
            val response = apiService.getPhotos(page = page)

            if (!response.isSuccessful) {

            }

            val photos = response.body() ?: emptyList()

            return LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }



}