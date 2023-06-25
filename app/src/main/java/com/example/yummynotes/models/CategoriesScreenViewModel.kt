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
import kotlinx.coroutines.launch

class CategoriesScreenViewModel (private val repository: RecipeRepository): ViewModel(){
    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    var categories by mutableStateOf(listOf<Categories>())



    init{
        viewModelScope.launch {
            repository.getAllFavorites().collect{
                    listOfRecipes -> _recipes.value = listOfRecipes

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

    fun getRecipeByCategory(categories: List<Categories>): List<Recipe> {
        val filteredRecipes = _recipes.value.filter { recipe ->
            recipe.category.any { it in categories }
        }

        return filteredRecipes
    }

}
