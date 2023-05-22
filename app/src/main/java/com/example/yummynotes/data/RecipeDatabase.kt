package com.example.yummynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.yummynotes.models.Recipe
//import com.example.yummynotes.utils.CustomConverters
import com.example.yummynotes.workers.SeedDatabaseWorker


@Database(
    entities = [Recipe::class],  // tables in the db
    version = 1,                // version of db schema
    exportSchema = false        // for schema version history updates
)
//@TypeConverters (CustomConverters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao   // Dao instance so that the DB knows about the Dao
    // add more daos here if you have multiple tables
    //later we might need more daos
    // declare as singleton - companion objects are like static variables in Java
    companion object {
        @Volatile   // never cache the value of Instance
        private var Instance: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase{
            return Instance ?: synchronized(this) { // wrap in synchronized block to prevent race conditions
                Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_db")
                    .addCallback(
                        object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                // do work on first db creation
                              val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                                WorkManager.getInstance(context).enqueue(request)
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                // do work on each start
                            }
                        }
                    )
                    .fallbackToDestructiveMigration()   // if schema changes wipe the whole schema - you could add your migration strategies here
                    .build()    // create an instance of the db
                    .also {
                        Instance = it   // override Instance with newly created db
                    }
            }
        }
    }
}

