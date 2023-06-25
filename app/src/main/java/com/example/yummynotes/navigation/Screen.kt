package com.example.yummynotes.navigation

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash")
    object MainScreen : Screen("main")

    object CategoriesScreen : Screen("categories")

    object RecipeScreen : Screen("recipe/{recipeID}") {
        fun withId(recipeID: Int): String {
            return this.route.replace(oldValue = "{recipeID}", newValue = recipeID.toString())
        }
    }
    object FavoritesScreen: Screen("favorites")
    object AddScreen : Screen("add/{recipeID}") {
        fun withId(recipeID: Int): String {
            return this.route.replace(oldValue = "{recipeID}", newValue = recipeID.toString())
        }
    }

}