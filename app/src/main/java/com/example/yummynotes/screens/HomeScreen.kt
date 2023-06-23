package com.example.yummynotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yummynotes.data.RecipeDao
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.models.getRecipes

import com.example.yummynotes.widgets.TopNavigationBar


import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.repository.RecipeRepository
import com.example.yummynotes.utils.Injector
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeViewModel) {

    Column{
        TopNavigationBar("Meine Rezepte", navController)
        RecipeList(viewModel = viewModel, navController = navController)
    }
}


@Composable
fun RecipeList(
navController: NavController,
viewModel: RecipeViewModel) {
    val viewModel: RecipeViewModel = viewModel(factory = Injector.provideRecipeViewModelFactory(
        LocalContext.current))
    val recipesState by viewModel.recipes.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(modifier = Modifier.background(color = Color.LightGray)){
        items(items = viewModel.recipes.value) { recipeItem -> //aus der Liste recipes bekommt es der Reihe nach Elemente übergeben --> geh durch die Liste
            RecipeRow(
                recipe = recipeItem,
                onRecipeClick =  { recipeID: Int ->
                    navController.navigate(Screen.RecipeScreen.withId(recipeID)) },
                onFavIconClick = { recipe ->
                    coroutineScope.launch {
                        viewModel.toggleFavorite(recipe)
                    }
                }

            )
        }

    }
}

@Composable
fun RecipeRow(recipe: Recipe,
              onRecipeClick: (Int) -> Unit,
              onFavIconClick: (Recipe) -> Unit = {}
) { //später werden mehrere Parameter eingefügt
    var imageID: Int = if (recipe.images.isEmpty()) {
        R.drawable.no_photos
    } else {
        recipe.images[0]
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        .clickable {
            onRecipeClick(recipe.id)
        },

        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.Gray

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            //horizontalAlignment = Alignment.End
        ) {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text("${recipe.title}\n${recipe.description}",
                modifier = Modifier.height(80.dp)
                    .padding(10.dp))
                Spacer(modifier= Modifier.width(5.dp))

                Icon(imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Lieblingsrezepte",
                    modifier = Modifier.clickable {  }
                )

               // Text("Favorites")

        }
            //later add the row and box etc when needed
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)

            ) {
                Image(painter = painterResource(id = imageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )


            }


        }
    }}
//hier können UI Elemente verschachtelt werden



