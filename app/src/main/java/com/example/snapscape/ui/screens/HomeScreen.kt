package com.example.snapscape.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.snapscape.R
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.model.Urls

@Composable
fun ImageCard(urls : Urls) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Text(urls.small.toString())
        AsyncImage(model = urls.full , contentDescription = null , contentScale = ContentScale.Crop)
    }
}
@Composable
fun imagepassing(viewModel: HomeScreenViewModel)
{ val x  = viewModel.imageData.collectAsState()
    LazyColumn {
        items(x.value)
        {u->
            ImageCard(u)

        }

    }

}
