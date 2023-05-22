package com.example.yummynotes.repositories

import com.example.yummynotes.data.RecipeDao
import com.example.yummynotes.models.Recipe
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val recipeDao: RecipeDao) {
    suspend fun addRecipe(recipe: Recipe) = recipeDao.add(recipe = recipe)

    suspend fun updateRecipe(recipe: Recipe) = recipeDao.update(recipe = recipe)

    //suspend fun deleteRecipe(recipe: Recipe) = recipeDao.delete(recipe = recipe)

 //   fun getAllRecipes() : Flow<List<Recipe>> = recipeDao.getAll()

//    fun getFavoriteRecipes() : Flow<List<Recipe>> = recipeDao.getFavorites()

  //  fun getById(id: Long) : Flow<Recipe?> = recipeDao.get(id)

  /*  companion object {
        // For Singleton instantiation
        @Volatile private var instance: RecipeRepository? = null

        fun getInstance(dao: RecipeDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }

   */
}