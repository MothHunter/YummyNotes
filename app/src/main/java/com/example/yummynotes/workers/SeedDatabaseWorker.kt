package com.example.yummynotes.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.yummynotes.data.RecipeDatabase
import com.example.yummynotes.models.getRecipes
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = RecipeDatabase.getDatabase(applicationContext)
            populateDatabase(database)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }

    private suspend fun populateDatabase(database: RecipeDatabase){
        Log.d(TAG, "populating database")
        val dao = database.recipeDao()

        dao.deleteAllRecipes()
        getRecipes().forEach{
            dao.add(it)
        }
    }
}