package com.example.yummynotes.models


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch



class AddEditScreenViewModel(private val repository: RecipeRepository, val recipeID: Int): ViewModel() {
    val recipeState = MutableStateFlow(Recipe())


    // solution explained at
    // https://medium.com/androiddevelopers/effective-state-management-for-textfield-in-compose-d6e5b070fbe5
    // needs imports: import androidx.compose.runtime.setValue / .getValue
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var ingredients by mutableStateOf("")
    var instructions by mutableStateOf("")
    // TODO: change initial value to false as soon as input requirements are implemented
    var buttonEnabled by mutableStateOf(true)
    var categories by mutableStateOf(listOf<Categories>())
    var images by mutableStateOf(mutableListOf<String>())

    init {
        viewModelScope.launch {
            if (recipeID != NEW_RECIPE) {
                repository.getRecipeById(recipeID).collect {
                    recipeState.value = it
                    title = it.title
                    description = it.description
                    ingredients = it.ingredients
                    instructions = it.instructions
                    categories = it.category
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

    fun toggleCategory(category: Categories) {
        var list = categories.toMutableList()
        if (list.contains(category)) {
            list.remove(category)
        } else {
            list.add(category)
        }
        categories = list
    }


    suspend fun addRecipe() {
        val newRecipe = Recipe(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            images = images,
            category = categories,
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
            category = categories,
            //images =  images
        )

        repository.updateRecipe(updatedRecipe)
    }
}

