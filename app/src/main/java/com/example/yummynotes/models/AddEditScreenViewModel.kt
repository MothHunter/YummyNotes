package com.example.yummynotes.models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddEditScreenViewModel(private val repository: RecipeRepository, val recipeID: Int): ViewModel() {
    var recipe = MutableStateFlow(Recipe())
    init {
        viewModelScope.launch {
            if (recipeID >= 0) {
                repository.getRecipeById(recipeID).collect {
                    //TODO: this should probably be made null-safe
                    //      but that then needs to be change also in repository, etc.
                        recipe.value = it

                }
            }
        }
    }



    suspend fun addRecipe(title: String,
                          description: String,
                          ingredients: String,
                          instructions: String,
                          images: List<Int>,
                          categories: List<Categories>,
                          isFavorite: Boolean
    ) {
        val recipe = Recipe(title = title,
                            description = description,
                            ingredients = ingredients,
                            instructions = instructions,
                            images = images,
                            category = categories,
                            isFavorite = isFavorite
        )
        repository.addRecipe(recipe)
    }
}