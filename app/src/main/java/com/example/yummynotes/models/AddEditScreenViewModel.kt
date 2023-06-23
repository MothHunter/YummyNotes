package com.example.yummynotes.models


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class AddEditScreenViewModel(private val repository: RecipeRepository, val recipeID: Int): ViewModel() {
    val recipeState = MutableStateFlow(Recipe())

    // solution explained at
    // https://medium.com/androiddevelopers/effective-state-management-for-textfield-in-compose-d6e5b070fbe5
    // needs imports: import androidx.compose.runtime.setValue / .getValue
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var ingredients by mutableStateOf("")
    var instructions by mutableStateOf("")

    init {
        viewModelScope.launch {
            if (recipeID >= 0) {
                repository.getRecipeById(recipeID).collect {
                    recipeState.value = it
                    title = it.title
                    description = it.description
                    ingredients = it.ingredients
                    instructions = it.instructions

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