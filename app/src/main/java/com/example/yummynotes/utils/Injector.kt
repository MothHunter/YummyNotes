package com.example.yummynotes.utils

//import RecipeScreenViewModelFactory
import android.content.Context
import com.example.yummynotes.data.RecipeDatabase
import com.example.yummynotes.models.AddEditScreenViewModelFactory
import com.example.yummynotes.models.FavoriteViewModelFactory
import com.example.yummynotes.models.HomeScreenViewModelFactory
import com.example.yummynotes.models.RecipeScreenViewModelFactory

import com.example.yummynotes.repository.RecipeRepository

object Injector {
    private fun getRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(RecipeDatabase.getDatabase(context.applicationContext).recipeDao())
    }

    fun provideHomeScreenViewModelFactory(context: Context): HomeScreenViewModelFactory {
        val repository = getRecipeRepository(context)
        return HomeScreenViewModelFactory(repository)
    }


    fun provideFavoriteViewModelFactory(context: Context): FavoriteViewModelFactory {
        val repository = getRecipeRepository(context)
        return FavoriteViewModelFactory(repository)
    }

    fun provideAddEditScreenViewModelFactory(context: Context, recipeID: Int): AddEditScreenViewModelFactory {
        val repository = getRecipeRepository(context)
        return AddEditScreenViewModelFactory(repository, recipeID)
    }

    fun provideRecipeScreenViewModelFactory(context: Context, recipeID: Int): RecipeScreenViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeScreenViewModelFactory(repository, recipeID = recipeID)
    }
    //same for others
}