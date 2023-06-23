package com.example.yummynotes.models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddEditScreenViewModel(private val repository: RecipeRepository, val recipeID: Int): ViewModel() {
    val recipeState = MutableStateFlow(Recipe())
    val titleState = MutableStateFlow("")

    init {
        viewModelScope.launch {
            if (recipeID >= 0) {
                repository.getRecipeById(recipeID).collect {
                    //TODO: this should probably be made null-safe
                    //      but that then needs to be change also in repository, etc.
                    recipeState.value = it
                    titleState.value = it.title

                }
            }
        }
    }


    suspend fun addRecipe(
        title: String,
        description: String,
        ingredients: String,
        instructions: String,
        images: List<Int>,
        categories: List<Categories>,
        isFavorite: Boolean
    ) {
        val newRecipe = Recipe(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            images = images,
            category = categories,
            isFavorite = isFavorite
        )
        repository.addRecipe(newRecipe)
    }


    suspend fun updateRecipe(
        title: String,
        description: String,
        ingredients: String,
        instructions: String,
        images: List<Int>,
        categories: List<Categories>,
        isFavorite: Boolean
    ) {
        val updatedRecipe = recipeState.value.copy(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            images = images,
            category = categories,
            isFavorite = isFavorite
        )

        repository.updateRecipe(updatedRecipe)
    }
}