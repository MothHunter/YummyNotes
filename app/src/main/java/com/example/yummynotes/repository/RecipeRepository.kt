package com.example.yummynotes.repository

import com.example.yummynotes.data.RecipeDao
import com.example.yummynotes.models.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    suspend fun addRecipe(recipe: Recipe) = recipeDao.add(recipe)

    suspend fun updateRecipe(recipe: Recipe) = recipeDao.update(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.delete(recipe)

    //fun getAllRecipes() = recipeDao.getAllRecipes()
    fun getAllRecipes() : Flow<List<Recipe>> = recipeDao.getAllRecipes()

    fun getRecipeById(id: Int): Flow<Recipe> = recipeDao.getRecipeByID(id)

    fun getAllFavorites(): Flow<List<Recipe>> {
        return recipeDao.getAllFavorites()

        //hab ich das richtige flow importiert @Florian?
    }
/*
    fun getById(id: Long) : Flow<Recipe?> = recipeDao.get(id)

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }*/
}