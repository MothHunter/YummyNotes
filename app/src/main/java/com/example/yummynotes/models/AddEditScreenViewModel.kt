package com.example.yummynotes.models


import android.util.Log
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
    // TODO: change initial value to false as soon as input validation is implemented
    var buttonEnabled by mutableStateOf(true)

    // TODO: add fields for images and categories

    init {
        viewModelScope.launch {
            if (recipeID != NEW_RECIPE) {
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

    // TODO: validate values!


    suspend fun onAddEditButtonClick() {

        if (recipeID == NEW_RECIPE) {
            addRecipe()
        } else {
            updateRecipe()
        }

    }


    suspend fun addRecipe() {
        val newRecipe = Recipe(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            images = listOf<Int>(),
            category = emptyList(),
            isFavorite = false
        )
        repository.addRecipe(newRecipe)
    }


    suspend fun updateRecipe() {
        val updatedRecipe = recipeState.value.copy(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            // TODO: this needs to be included, but fields currently still missing
            /*
            images = images,
            category = categories,
            isFavorite = isFavorite

             */
        )

        repository.updateRecipe(updatedRecipe)
    }
}