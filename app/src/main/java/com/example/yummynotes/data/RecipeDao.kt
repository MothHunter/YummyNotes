package com.example.yummynotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yummynotes.models.Recipe
import kotlinx.coroutines.flow.Flow


@Dao

interface RecipeDao {
    @Insert
    suspend fun add(recipe: Recipe)

    @Update
    suspend fun update(recipe:Recipe)

   // @Delete
    //bei lackner suspend
   // fun delete(recipe: Recipe)

   // @Query("SELECT * from recipe WHERE isFavorite = 1")
   // fun getFavorites(): Flow<List<Recipe>>

   // @Query("SELECT * from recipe WHERE dbId = :id")
  //  fun get(id: Long): Flow<Recipe>

   // @Query("DELETE from recipe")
  //  suspend fun deleteAll()
    /* our structure:
    Recipe(
            id = 1,
            title = "Pizza",
            description = "Pizza mit Salami",
            ingredients = "Pizzateig, Tomatensauce, Mozerella, Salami",
            instructions = "Die Pizza bei 180 Grad in den Ofen schieben",
            images = listOf(R.drawable.pizza)
        )
     */
}