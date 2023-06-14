package com.example.yummynotes.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private var _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    /*val recipesList: List<Recipe> = recipes.to
        get() = _recipes.to
    private val recipeList = getRecipes().toMutableStateList()
    val recipes: List<Recipe>
        get() = _recipes*/

    init {
        viewModelScope.launch {
            repository.getAllRecipes().distinctUntilChanged()
                .collect { listOfRecipes ->
                    if (listOfRecipes.isNullOrEmpty()) {
                        Log.d("RecipeViewModel", "No Recipes")
                    } else {
                        val baseRecipes = getRecipes()
                        for(recipe in baseRecipes){
                            repository.addRecipe(recipe)
                        }
                        _recipes = MutableStateFlow(listOf<Recipe>())
                    }
                }
        }
    }

   fun getRecipeByID(recipeID: Int): Recipe {
        recipeID.let {
            return (_recipes.value.filter { it.id == recipeID })[0]
        }
    }

    //fun getRecipeByID(recipeID: Int?) = recipes.value.filter { it.id == recipeID }[0]

    suspend fun toggleFavorite(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        repository.updateRecipe(recipe)
    }

}