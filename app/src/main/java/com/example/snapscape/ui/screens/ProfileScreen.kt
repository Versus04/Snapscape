package com.example.snapscape.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.snapscape.model.User

@Composable
fun ProfilePage(profile : User)
{
    Text(text = profile.name)

}