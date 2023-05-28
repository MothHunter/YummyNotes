package com.example.yummynotes.repository

import com.example.yummynotes.data.RecipeDao
import com.example.yummynotes.models.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    suspend fun addRecipe(recipe: Recipe) = recipeDao.add(recipe)

    suspend fun updateRecipe(recipe: Recipe) = recipeDao.update(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.delete(recipe)

    fun getAllRecipes() = recipeDao.getAllRecipes()

    fun getRecipeById(id: Int) = recipeDao.getRecipeByID(id)

    fun getAllFavorites(): Flow<List<Recipe>> {
        return recipeDao.getAllFavorites()

        //hab ich das richtige flow importiert @Florian?
    }
}