package com.example.snapscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapscape.ui.screens.HomeScreenViewModel
import com.example.snapscape.ui.screens.ImageCard
import com.example.snapscape.ui.screens.imagepassing
import com.example.snapscape.ui.theme.SnapScapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val viewModel: HomeScreenViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            SnapScapeTheme {

                imagepassing(viewModel)
            }
        }
    }
}

