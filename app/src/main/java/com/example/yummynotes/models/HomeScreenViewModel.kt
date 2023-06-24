package com.example.yummynotes.models

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    //private  var  textToSpeech: TextToSpeech? = null
    /*val recipesList: List<Recipe> = recipes.to
        get() = _recipes.to
    private val recipeList = getRecipes().toMutableStateList()
    val recipes: List<Recipe>
        get() = _recipes*/


    init {
        viewModelScope.launch {
            //Log.d("HomeScreenVM", "add movies completed")
            Log.d("HomeScreenVM", "coroutine launched")

            repository.getAllRecipes().collect { recipeList ->
                _recipes.value = recipeList
            }
        }
    }



    suspend fun toggleFavorite(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        repository.updateRecipe(recipe)
    }

}