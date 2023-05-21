package com.example.yummynotes.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RecipeViewModel() : ViewModel() {

    private val _recipes = getRecipes().toMutableStateList()
    val recipes: List<Recipe>
        get() = _recipes

    fun getRecipeByID(recipeID: Int): Recipe {
        recipeID.let {
            return (_recipes.filter { it.id == recipeID})[0]
        }
    }

}