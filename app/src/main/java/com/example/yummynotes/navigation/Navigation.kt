package com.example.yummynotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yummynotes.data.RecipeDatabase
import com.example.yummynotes.models.AddAndEditScreenViewModel
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.models.RecipeViewModelFactory
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.repository.RecipeRepository
import com.example.yummynotes.screens.EditAndAddScreen
import com.example.yummynotes.screens.RecipeScreen
import com.example.yummynotes.screens.SplashScreen

@Composable
fun Navigation() {
    //holt sich die Routes aus der Sealed Class
    val navController = rememberNavController()
    val db = RecipeDatabase.getDatabase(LocalContext.current)
    val repository = RecipeRepository(recipeDao = db.recipeDao())
    val factory = RecipeViewModelFactory(repository)
    val viewModel: RecipeViewModel = viewModel(factory = factory)
    //val addAndEditScreenViewModel = AddAndEditScreenViewModel()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, viewModel)
        }
        composable(route = Screen.RecipeScreen.route,
            arguments = listOf(navArgument(name = "recipeID") {type = NavType.IntType}))
            {
            backStackEntry ->
                RecipeScreen(navController = navController,
                    viewModel = viewModel,
                    recipeID = backStackEntry.arguments?.getInt("recipeID") ?: 0)
        }

        composable(route = Screen.EditAndAddScreen.route){
                backStackEntry ->
                    EditAndAddScreen(navController = navController,
                                    viewModel = viewModel,
                                     recipeID = backStackEntry.arguments?.getInt("recipeID") ?: 0)
        }
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
    }
}