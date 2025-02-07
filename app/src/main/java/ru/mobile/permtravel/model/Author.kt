package ru.mobile.permtravel.model

import androidx.annotation.DrawableRes

data class Author(
    val id: Int,
    val name: String,
    //val avatarUrl: String // использовать glide
    @DrawableRes val imageRes: Int //Ссылка на ресурс drawable
)
