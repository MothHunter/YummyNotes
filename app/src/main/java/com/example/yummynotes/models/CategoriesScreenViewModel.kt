package com.example.yummynotes.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class CategoriesScreenViewModel (private val repository: RecipeRepository): ViewModel(){
    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    var categories by mutableStateOf(listOf<Categories>())
    var filteredRecipes = MutableStateFlow(listOf<Recipe>())



    init{
        viewModelScope.launch {
            repository.getAllFavorites().collect{
                    listOfRecipes -> if(listOfRecipes.isNullOrEmpty()){
                Log.d("CategoryScreenViewModel", "No Recipes")
            } else {
                _recipes.value = listOfRecipes
            }
            }
        }
    }

    fun toggleCategory(category: Categories) {
        var list = categories.toMutableList()
        if (list.contains(category)) {
            list.remove(category)
        } else {
            list.add(category)
        }
        categories = list
    }

    fun getRecipeByCategory(categories: List<Categories>) {
         filteredRecipes = _recipes.asStateFlow() { recipe ->
            recipe.category.any { it in categories }
        }
    }

    suspend fun toggleFavorite(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        repository.updateRecipe(recipe)
    }

}
