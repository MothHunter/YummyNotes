package com.example.yummynotes.screens

//import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yummynotes.RecipeList
import com.example.yummynotes.RecipeRow
import com.example.yummynotes.data.RecipeDatabase
import com.example.yummynotes.models.FavoriteViewModel
import com.example.yummynotes.models.FavoriteViewModelFactory
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.RecipeScreenViewModelFactory
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.repository.RecipeRepository
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.SimpleTopAppBar
import com.example.yummynotes.widgets.TopNavigationBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {
    val db = RecipeDatabase.getDatabase(LocalContext.current)
    val repository = RecipeRepository(recipeDao = db.recipeDao())
    val factory = FavoriteViewModelFactory(repository)
    val viewModel: FavoriteViewModel = viewModel(factory = factory)
    val recipeList by viewModel.recipes.collectAsState()
    val recipeState = viewModel.recipes
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() })
        {
            Text(text = "Meine Lieblingsrezepte")

        }
    }) { //content padding not used
            padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(items = recipeState) { recipeItem ->
                    RecipeRow(
                        recipe = recipeItem,
                        onRecipeClick = { recipeID: Int ->
                            navController.navigate(Screen.RecipeScreen.withId(recipeID))
                        },
                        onFavIconClick = { recipe ->
                            coroutineScope.launch {
                                viewModel.toggleFavorite(recipe)
                            }
                        }

                    )
                }

            }


        }
    }
}