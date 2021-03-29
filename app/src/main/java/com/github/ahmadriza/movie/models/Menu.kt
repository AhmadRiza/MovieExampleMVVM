package com.github.ahmadriza.movie.models

data class MenuItem(
    val title: String,
    val onClick: () -> Unit
)