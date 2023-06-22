package com.example.yummynotes.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yummynotes.repository.RecipeRepository

class AddEditScreenViewModelFactory (private val recipeRepository: RecipeRepository,
                                     private val recipeID: Int): ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditScreenViewModel::class.java)) {
            return AddEditScreenViewModel(recipeRepository, recipeID = recipeID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}