package com.sinannuhoglu.fooddeliveryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketScreen
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel
import com.sinannuhoglu.fooddeliveryapp.ui.detail.DetailScreen
import com.sinannuhoglu.fooddeliveryapp.ui.favorite.FavScreen
import com.sinannuhoglu.fooddeliveryapp.ui.home.HomeScreen
import com.sinannuhoglu.fooddeliveryapp.ui.login.LoginPage
import com.sinannuhoglu.fooddeliveryapp.ui.register.RegisterPage
import com.sinannuhoglu.fooddeliveryapp.ui.welcome.WelcomePage

object Routes {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val BASKET = "basket"
    const val DETAIL = "detail/{item}"
}

@Composable
fun FoodNavGraph(
    navController: NavHostController,
    basketViewModel: BasketViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {
        composable(Routes.WELCOME) {
            WelcomePage(
                onSignInClick = { navController.navigate(Routes.LOGIN) },
                onSignUpClick = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.LOGIN) {
            LoginPage(navController = navController)
        }

        composable(Routes.REGISTER) {
            RegisterPage(navController = navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Routes.FAVORITES) {
            FavScreen(navController = navController)
        }

        composable(Routes.BASKET) {
            BasketScreen(navController = navController, viewModel = basketViewModel)
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("item") { type = androidx.navigation.NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("item")
            val foodItem = Gson().fromJson(json, FoodItem::class.java)
            DetailScreen(navController = navController, foodItem = foodItem, basketViewModel = basketViewModel)
        }
    }
}
