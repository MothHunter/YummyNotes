package com.example.yummynotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.widgets.TopNavigationBar

@Composable
fun RecipeScreen(navController: NavController, viewModel: RecipeViewModel, recipeID: Int?) {
    Column{
        TopNavigationBar("Recipies", navController)
    }
}