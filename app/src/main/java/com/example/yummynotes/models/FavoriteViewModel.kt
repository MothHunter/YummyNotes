package com.example.yummynotes.models

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FavoriteViewModel(private val repository: RecipeRepository)  : ViewModel(){
    private val _favoriteState = MutableStateFlow(listOf<Recipe>())
    val favoriteState: StateFlow<List<Recipe>> = _favoriteState.asStateFlow()


    init{
        viewModelScope.launch {
            repository.getAllFavorites().collect{
                listOfRecipes -> _favoriteState.value = listOfRecipes
            }
        }
    }

    suspend fun updateFavorites(recipe: Recipe){
        recipe.isFavorite = recipe.isFavorite
        repository.updateRecipe(recipe)
    }

}

