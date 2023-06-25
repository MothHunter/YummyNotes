package com.example.yummynotes.screens

//import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yummynotes.R
import com.example.yummynotes.RecipeRow
import com.example.yummynotes.models.FavoriteViewModel
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.SimpleAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {
    val viewModel: FavoriteViewModel = viewModel(factory = Injector.provideFavoriteViewModelFactory(
        LocalContext.current))
    val recipeList by viewModel.recipes.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        SimpleAppBar(title = stringResource(id = R.string.favorite_recipes), arrowBackClicked = { navController.popBackStack() })
        {        }
    }) { //content padding not used
            padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(items = recipeList) { recipeItem ->
                    RecipeRow(
                        recipe = recipeItem,
                        onRecipeClick = { recipeID: Int ->
                            navController.navigate(Screen.RecipeScreen.withId(recipeID))
                        },
                        onFavIconClick = { recipe ->
                            coroutineScope.launch {
                                viewModel.updateFavorites(recipe)
                            }
                        }

                    )
                }

            }


        }
    }
}