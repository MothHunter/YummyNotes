package com.example.yummynotes.models


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class AddEditScreenViewModel(private val repository: RecipeRepository, val recipeID: Int): ViewModel() {
    val recipeState = MutableStateFlow(Recipe())
/*
    private var _categories = MutableStateFlow(listOf<Categories>())
    val categories: StateFlow<List<Categories>> = _categories.asStateFlow()

 */
    var categories by mutableStateOf(listOf<Categories>())

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
/*
    fun toggleCategory(category: Categories) {
        val list = _categories.value.toMutableList()
        if (list.contains(category)) {
            list.remove(category)
        } else {
            list.add(category)
        }
        _categories.value = list
    }

 */


    suspend fun addRecipe() {
        val newRecipe = Recipe(
            title = title,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            images = listOf<Int>(),
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
            // TODO: this needs to be included, but fields currently still missing
            /*
            images = images,
            isFavorite = isFavorite

             */
        )

        repository.updateRecipe(updatedRecipe)
    }
}

