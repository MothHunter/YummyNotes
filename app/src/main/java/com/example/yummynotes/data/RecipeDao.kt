package com.example.yummynotes.data

import androidx.room.*
import com.example.yummynotes.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun add(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE isFavorite = 1")

    //@Florian: wie kann man das 1 durch true ersetzen?
    fun getAllFavorites(): Flow<List<Recipe>>
    @Query("SELECT * FROM recipe WHERE id =:recipeId")
    fun getRecipeByID(recipeId: Int) : Flow<Recipe>
}