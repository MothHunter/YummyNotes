package com.example.yummynotes

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.screens.RecipeScreen

@Composable
fun Navigation() {
    //holt sich die Routes aus der Sealed Class
    val navController = rememberNavController()
    val viewModel = RecipeViewModel()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(navController = navController, viewModel = viewModel, recipeID = 0)
        }
    }

}