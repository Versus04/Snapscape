package com.example.snapscape.ui.screens

sealed class Screens(val route : String  , val title : String) {
    object Home : Screens("home","Home")
    object ProfilePage : Screens("profile_page","Profile Page")
    object ImagePage : Screens("image_page" ,"")
}