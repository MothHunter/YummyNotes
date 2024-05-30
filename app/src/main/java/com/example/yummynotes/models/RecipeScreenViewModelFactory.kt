package com.example.yummynotes.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yummynotes.models.RecipeScreenViewModel
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope

class RecipeScreenViewModelFactory(
    private val recipeRepository: RecipeRepository,
    private val recipeID: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeScreenViewModel::class.java)) {
            return RecipeScreenViewModel(recipeRepository, recipeID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
