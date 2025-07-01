package com.sinannuhoglu.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sinannuhoglu.fooddeliveryapp.navigation.FoodNavGraph
import com.sinannuhoglu.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val basketViewModel: BasketViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryAppTheme {
                val navController = rememberNavController()
                FoodNavGraph(navController = navController, basketViewModel = basketViewModel)
            }
        }
    }
}
