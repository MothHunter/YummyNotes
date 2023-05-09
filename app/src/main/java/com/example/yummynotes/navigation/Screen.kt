package com.example.yummynotes.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")

    object RecipeScreen : Screen("recipe/recipeID")
}