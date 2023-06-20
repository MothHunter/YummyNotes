package com.example.yummynotes.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.getRecipes
import com.example.yummynotes.workers.SeedDatabaseWorker

@Database(
    entities = [Recipe::class],
    version = 6,
    exportSchema = false
)

@TypeConverters(CustomConverters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            Log.d("RecipeDatabase", "getting database...")
            return instance ?: synchronized(this) {
                // synchronized = only one thread can access this at any time!
                Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_db")
                    .addCallback(
                        object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Log.d("RecipeDatabase", "... for the first time")
                                val workerRequest = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                                WorkManager.getInstance(context).enqueue(workerRequest)

                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                Log.d("RecipeDatabase", "... using existing database")
                            }
                        }
                    )
                    .fallbackToDestructiveMigration() // NOTE: this does not re-run the dbWorker!
                    .build()
                    .also {
                        instance = it
                        Log.d("RecipeDatabase", "getDatabase function completed")
                    }
            }
        }
    }
}