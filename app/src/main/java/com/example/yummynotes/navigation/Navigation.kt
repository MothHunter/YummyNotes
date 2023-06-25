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
import com.example.yummynotes.models.HomeScreenViewModel
import com.example.yummynotes.models.HomeScreenViewModelFactory
import com.example.yummynotes.models.RecipeScreenViewModel
import com.example.yummynotes.models.RecipeScreenViewModelFactory
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.repository.RecipeRepository
import com.example.yummynotes.screens.AddScreen
import com.example.yummynotes.screens.CategoriesScreen
import com.example.yummynotes.screens.RecipeScreen
import com.example.yummynotes.screens.SplashScreen

@Composable
fun Navigation() {
    //holt sich die Routes aus der Sealed Class
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)

        }
        composable(route = Screen.RecipeScreen.route,
            arguments = listOf(navArgument(name = "recipeID") {type = NavType.IntType}))
            {
            backStackEntry ->
                RecipeScreen(navController = navController,
                    recipeID = backStackEntry.arguments?.getInt("recipeID") ?: 0)
        }

        composable(route = Screen.AddScreen.route,
            arguments = listOf(navArgument(name = "recipeID") {type = NavType.IntType}))
        {
                backStackEntry ->
                    AddScreen(navController = navController,
                        recipeID = backStackEntry.arguments?.getInt("recipeID") ?: 0)
        }
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }

        composable(route = Screen.CategoriesScreen.route)
        {
            CategoriesScreen(navController = navController)
        }
    }
}