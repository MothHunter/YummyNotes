package com.example.yummynotes.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class FavoriteViewModel(private val repository: RecipeRepository): ViewModel(){


    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
   // var favorites by mutableStateOf(listOf<Recipe>())
  //  var filteredReciües = MutableStateFlow(listOf<Recipe>())

    init{
        viewModelScope.launch {
            repository.getAllFavorites().collect{
                listOfRecipes -> _recipes.value = listOfRecipes
            }
        }
    }

    suspend fun updateFavorites(recipe: Recipe){
        recipe.isFavorite = recipe.isFavorite
        repository.updateRecipe(recipe)
    }

}

