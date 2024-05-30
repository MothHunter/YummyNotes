package com.example.yummynotes.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yummynotes.repository.RecipeRepository

class CategoriesScreenViewModelFactory (private val recipeRepository: RecipeRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesScreenViewModel::class.java)) {
            return CategoriesScreenViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}