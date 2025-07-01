package com.sinannuhoglu.fooddeliveryapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.network.ApiService
import com.sinannuhoglu.fooddeliveryapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _allFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    private val _filteredFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    val filteredFoods: StateFlow<List<FoodItem>> = _filteredFoods

    private val _favorites = MutableStateFlow<List<FoodItem>>(emptyList())
    val favorites: StateFlow<List<FoodItem>> = _favorites

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchFoods()
    }

    /**
     * API'den yemek listesini çeker ve state'leri günceller.
     */
    private fun fetchFoods() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getFoodList()
                if (response.isSuccessful) {
                    val foods = response.body()?.yemekler
                        ?.filterNotNull()
                        ?.mapIndexed { index, yemek ->
                            FoodItem(
                                id = index,
                                name = yemek.yemekAdi.orEmpty(),
                                price = yemek.yemekFiyat?.toIntOrNull() ?: 0,
                                imageUrl = Constants.IMAGE_BASE_URL + yemek.yemekResimAdi.orEmpty(),
                                isFavorite = false
                            )
                        }.orEmpty()

                    _allFoods.value = foods
                    _filteredFoods.value = foods
                }
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Arama sorgusuna göre listeyi filtreler.
     */
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _filteredFoods.value = _allFoods.value.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    /**
     * Favori durumunu değiştirir ve favoriler listesini günceller.
     */
    fun toggleFavorite(food: FoodItem) {
        val updatedList = _allFoods.value.toMutableList()
        val index = updatedList.indexOfFirst { it.id == food.id }
        if (index != -1) {
            val updatedItem = updatedList[index].copy(isFavorite = !updatedList[index].isFavorite)
            updatedList[index] = updatedItem
            _allFoods.value = updatedList
            _filteredFoods.value = updatedList
            _favorites.value = updatedList.filter { it.isFavorite }
        }
    }
}