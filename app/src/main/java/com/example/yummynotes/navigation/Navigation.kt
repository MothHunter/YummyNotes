package com.example.yummynotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yummynotes.models.AddAndEditScreenViewModel
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.screens.EditAndAddScreen
import com.example.yummynotes.screens.RecipeScreen
import com.example.yummynotes.screens.SplashScreen

@Composable
fun Navigation() {
    //holt sich die Routes aus der Sealed Class
    val navController = rememberNavController()
    val viewModel = RecipeViewModel()
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