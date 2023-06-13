package com.example.yummynotes.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel(repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getAllRecipes().distinctUntilChanged()
                .collect{ listOfRecipes ->
                    if(listOfRecipes.isNullOrEmpty()){
                        Log.d("MoviesViewModel", "Empty movies")
                    } else {
                        _recipes.value = listOfRecipes
                    }
                }
        }

    suspend fun getRecipeByID(recipeID: Int): Recipe {
        recipeID.let {
            return (_recipes.value.filter { it.id == recipeID})[0]
        }
    }

    suspend fun updateFavoriteState(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        repository.updateRecipe(recipe)
    }
    }

}