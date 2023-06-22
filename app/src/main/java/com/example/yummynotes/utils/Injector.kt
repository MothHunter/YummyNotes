package com.example.yummynotes.utils

import android.content.Context
import com.example.yummynotes.data.RecipeDatabase
import com.example.yummynotes.models.AddEditScreenViewModelFactory
import com.example.yummynotes.models.FavoriteViewModelFactory
import com.example.yummynotes.models.RecipeViewModelFactory
import com.example.yummynotes.repository.RecipeRepository

object Injector {
    private fun getRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(RecipeDatabase.getDatabase(context.applicationContext).recipeDao())
    }

    fun provideRecipeViewModelFactory(context: Context): RecipeViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeViewModelFactory(repository)
    }


    fun provideFavoriteViewModelFactory(context: Context): FavoriteViewModelFactory {
        val repository = getRecipeRepository(context)
        return FavoriteViewModelFactory(repository)
    }

    fun provideAddEditScreenViewModelFactory(context: Context, recipeID: Int): AddEditScreenViewModelFactory {
        val repository = getRecipeRepository(context)
        return AddEditScreenViewModelFactory(repository, recipeID)
    }
    //same for others
}