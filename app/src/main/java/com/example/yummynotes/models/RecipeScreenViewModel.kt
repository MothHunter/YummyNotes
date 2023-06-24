package com.example.yummynotes.models

import android.content.Context
import android.graphics.Bitmap
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummynotes.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.*

class RecipeScreenViewModel(
    private val repository: RecipeRepository,
    val recipeID: Int
) : ViewModel() {
    val recipeState = MutableStateFlow(Recipe())
    //delete or leave, not sure
    val images = recipeState.value.images
    var textToSpeech: TextToSpeech? = null


    init {
        viewModelScope.launch {
            repository.getRecipeById(recipeID).collect {
                recipeState.value = it
        }
    }

    suspend fun onDeleteButtonClick(recipeID: Int) {
        val recipe = repository.getRecipeById(recipeID).firstOrNull()
        recipe?.let {
            // Call your repository or data source method to delete the recipe
            repository.deleteRecipe(recipe)
        }
    }

    fun readText(context: Context, text: String) {
        textToSpeech = TextToSpeech(context) { result ->
            Log.d("TTS", result.toString())
            if (result == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.GERMAN //TODO: pick language dynamically
                    txtToSpeech.setSpeechRate(.0f)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }
}}
