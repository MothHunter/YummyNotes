package com.example.yummynotes.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")

    object RecipeScreen : Screen("recipe/{recipeID}") {
        fun withId(recipeID: Int): String {
            return this.route.replace(oldValue = "{recipeID}", newValue = recipeID.toString())
        }
    }

    object EditAndAddScreen : Screen("editandadd")
}