package com.sinannuhoglu.fooddeliveryapp.ui.home

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel
import com.sinannuhoglu.fooddeliveryapp.ui.bottomnav.BottomBar
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val foodList by viewModel.filteredFoods.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(foodList) {
        Log.d("HomeScreen", "Güncellenen ürün sayısı: ${foodList.size}")
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = UiConstants.gradientBackground)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = UiConstants.mainButtonColor)
                }
            } else {
                HomeScreenContent(
                    foodList = foodList,
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { viewModel.onSearchQueryChanged(it) },
                    onFavoriteClick = { item -> viewModel.toggleFavorite(item) },
                    onItemClick = { item ->
                        val json = Uri.encode(Gson().toJson(item))
                        Log.d("Navigation", "Gönderilen JSON: $json")
                        navController.navigate("detail/$json")
                    }
                )
            }
        }
    }
}
