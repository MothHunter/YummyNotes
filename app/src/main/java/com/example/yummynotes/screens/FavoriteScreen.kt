package com.example.yummynotes.screens

//import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.yummynotes.RecipeList
import com.example.yummynotes.RecipeRow
import com.example.yummynotes.models.FavoriteViewModel
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.repository.RecipeRepository
import com.example.yummynotes.widgets.TopNavigationBar

@Composable
fun FavoriteScreen(navController: NavController, repository: RecipeRepository){
    val favoriteViewModel= FavoriteViewModel(repository)

    val favoritestate by favoriteViewModel.favoriteState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column{
        TopNavigationBar("Favorites", navController)
        //RecipeList(viewModel.recipes, navController)
    }
/*
    @Composable
    fun FavoriteRow(recipes: List<Recipe>,
                    navController: NavController) {
        LazyColumn(modifier = Modifier.background(color = Color.LightGray)) {
            recipes.forEach { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onRecipeClick = { recipeID ->
                        navController.navigate(Screen.RecipeScreen.withId(recipeID))
                    },
                    onFavClick = { recipeID ->
                        // Handle favorite click event
                        coroutineScope.launch {
                            favoriteViewModel.updateFavoriteRecipes(recipe)
                        }
                    }
                )
            }
        }
    }


    }*/}