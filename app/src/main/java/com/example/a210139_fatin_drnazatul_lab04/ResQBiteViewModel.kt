package com.example.a210139_fatin_drnazatul_lab04

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResQBiteViewModel : ViewModel() {
    private val _foodList = MutableStateFlow(listOf(
        FoodItem("Extra Croissants", "1.2km", "NearBakery", R.drawable.croissant),
        FoodItem("Tin tuna", "0.8km", "Mira", R.drawable.canned_tuna),
        FoodItem("Roti Gardenia Pandan", "1.0km", "Amar", R.drawable.roti),
        FoodItem("Nasi Ayam", "0.3km", "Qila", R.drawable.nasiayam)
    ))

    val foodList: StateFlow<List<FoodItem>> = _foodList

    // add food list
    fun addFoodItem(item: FoodItem) {
        _foodList.value = _foodList.value + item
    }
}