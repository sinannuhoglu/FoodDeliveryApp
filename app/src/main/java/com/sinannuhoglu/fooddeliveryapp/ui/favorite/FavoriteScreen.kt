package com.sinannuhoglu.fooddeliveryapp.ui.favorite

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.sinannuhoglu.fooddeliveryapp.ui.components.FoodItemCard
import com.sinannuhoglu.fooddeliveryapp.ui.home.HomeViewModel

/**
 * FavScreen, kullanıcının favori olarak işaretlediği ürünlerin listelendiği ekrandır.
 *
 * Not: Bu projede favoriler için backend API desteği bulunmamaktadır.
 * Bu nedenle, favori yönetimi yalnızca local (yerel) düzeyde tutulmakta ve kalıcı veri senkronizasyonu sağlanamamaktadır.
 *
 * Bu sebeple ekranın geliştirilmesi durdurulmuştur ve ilerleyen süreçte API desteği eklendikten sonra
 * tekrardan revize edilerek kullanılabilir hale getirilecektir.
 */
@Composable
fun FavScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val favorites by viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Favori Ürünler",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hiç favori ürününüz yok",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(favorites) { item ->
                    FoodItemCard(
                        item = item,
                        onFavoriteClick = { viewModel.toggleFavorite(item) },
                        onItemClick = {
                            val json = Uri.encode(Gson().toJson(item))
                            navController.navigate("detail/$json")
                        }
                    )
                }
            }
        }
    }
}
