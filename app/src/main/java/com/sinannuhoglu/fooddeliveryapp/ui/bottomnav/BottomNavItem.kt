package com.sinannuhoglu.fooddeliveryapp.ui.bottomnav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
)
