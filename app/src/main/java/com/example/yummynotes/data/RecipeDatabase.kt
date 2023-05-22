package com.example.yummynotes.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yummynotes.models.Recipe

@Database(
    entities = [Recipe::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(CustomConverters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return instance ?: synchronized(this) {
                // synchronized => cannot be accessed by more than one thread at a time
                Room.databaseBuilder(context, RecipeDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it   // scope function for setting scope (=???)
                        Log.d("MovieDatabase", "created")
                    }
            }
        }
    }
}