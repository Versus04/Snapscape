package com.example.snapscape.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.ui.screens.Screens.ImagePage

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val navController = rememberNavController()
    val photos = viewModel.photos.collectAsLazyPagingItems()

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.route) {
                LazyColumn {
                    items(
                        count = photos.itemCount,
                        key = { index -> photos[index]?.id ?: index }
                    ) { index ->
                        photos[index]?.let { image ->
                            PostCard(
                                imageDto = image,
                                onImageClick = {
                                    navController.navigate("${Screens.ImagePage.route}/${image.id}")
                                }
                            )
                        }
                    }

                    photos.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { LoadingItem() }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { LoadingItem() }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorItem(
                                        message = "Error loading images",
                                        onRetryClick = { retry() }
                                    )
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorItem(
                                        message = "Error loading more images",
                                        onRetryClick = { retry() }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            composable(
                route = "${Screens.ImagePage.route}/{imageId}",
                arguments = listOf(navArgument("imageId") { type = NavType.StringType })
            ) { backStackEntry ->
                val imageId = backStackEntry.arguments?.getString("imageId")
                // Get the current list of items
                val currentList = photos.itemSnapshotList.items
                // Find the image with matching ID
                val selectedImage = currentList.find { it.id == imageId }
                
                selectedImage?.let { image ->
                    imagepage(image = image)  // Make sure this matches your actual image detail page composable name
                }
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(
    message: String,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Button(onClick = onRetryClick) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun PostCard(
    imageDto: UnsplashImageDTO,
    onImageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable(onClick = onImageClick)
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.clickable(enabled = true, onClick = {}),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = imageDto.user.profile_image.large,
                        contentDescription = null,
                        modifier = Modifier.clip(CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = imageDto.user.username,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(8.dp))
                AsyncImage(
                    model = imageDto.urls.regular,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}