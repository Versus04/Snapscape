package com.example.snapscape.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImage
import com.example.snapscape.model.UnsplashImageDTO

@Composable
fun imagepage(image :UnsplashImageDTO)
{
    LazyColumn(Modifier.fillMaxSize().padding(16.dp))
    {
        item {
            // Header with user info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = image.user.profile_image.large,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = image.user.username,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = image.user.name ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


    }
        item()
        {
            AsyncImage(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp) , model = image.urls.full, contentDescription = null)
        }
        item()
        {
            Text(image.description)

        }

}
}