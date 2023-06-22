package com.example.yummynotes.models

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    private  var  textToSpeech: TextToSpeech? = null
    /*val recipesList: List<Recipe> = recipes.to
        get() = _recipes.to
    private val recipeList = getRecipes().toMutableStateList()
    val recipes: List<Recipe>
        get() = _recipes*/


    init {
        viewModelScope.launch {
            //Log.d("HomeScreenVM", "add movies completed")
            Log.d("HomeScreenVM", "coroutine launched")

            repository.getAllRecipes().collect { movieList ->
                _recipes.value = movieList
            }
        }
    }

   fun getRecipeByID(recipeID: Int): Recipe {
        //recipeID.let {
            Log.d("recipeID", recipeID.toString())
            return (_recipes.value.filter { it.id == recipeID })[0]
        //}
    }

    //fun getRecipeByID(recipeID: Int?) = recipes.value.filter { it.id == recipeID }[0]

    suspend fun toggleFavorite(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        repository.updateRecipe(recipe)
    }

    fun readText(context: Context, text: String) {
        textToSpeech = TextToSpeech(context) {
            Log.d("TTS", it.toString())
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let {
                    txtToSpeech ->
                    txtToSpeech.language = Locale.GERMAN //TODO: pick language dynamically
                    txtToSpeech.setSpeechRate(.0f)
                    txtToSpeech.speak(text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }
}