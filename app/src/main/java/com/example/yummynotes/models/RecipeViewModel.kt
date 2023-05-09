package com.example.yummynotes.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class RecipeViewModel() : ViewModel() {
    private val _recipes = getRecipes().toMutableStateList()
    val recipes: List<Recipe>
        get() = _recipes

    fun getRecipeByID(recipeID: Int): Recipe {
        return (_recipes.filter { it.id == recipeID})[0]
    }
}