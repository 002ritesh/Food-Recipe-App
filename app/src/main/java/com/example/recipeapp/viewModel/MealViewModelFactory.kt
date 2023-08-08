package com.example.recipeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.db.MealDatebase

class MealViewModelFactory(
    private val mealDatebase: MealDatebase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealDatebase) as T
    }
}