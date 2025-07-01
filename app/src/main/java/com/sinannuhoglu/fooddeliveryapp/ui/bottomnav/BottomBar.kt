package com.sinannuhoglu.fooddeliveryapp.ui.bottomnav

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sinannuhoglu.fooddeliveryapp.R

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, R.string.home),
        BottomNavItem("favorites", R.drawable.ic_fav, R.string.fav),
        BottomNavItem("basket", R.drawable.ic_basket, R.string.basket)
    )

    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(containerColor = Color.White) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFE347C),
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
