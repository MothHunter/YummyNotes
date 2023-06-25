package com.example.yummynotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.screens.*

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

        composable(route = Screen.FavoritesScreen.route)
        {
            FavoriteScreen(navController = navController)
        }
    }
}