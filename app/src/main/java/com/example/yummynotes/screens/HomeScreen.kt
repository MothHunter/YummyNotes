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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.RecipeViewModel

import com.example.yummynotes.widgets.TopNavigationBar


import com.example.yummynotes.navigation.Screen


@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeViewModel) {

    Column{
        TopNavigationBar("Home", navController)
        RecipeList(viewModel = viewModel, navController = navController)
    }


}

@Composable
fun RecipeList(
navController: NavController,
viewModel: RecipeViewModel) {
    val recipes = viewModel.recipes

    LazyColumn(modifier = Modifier.background(color = Color.LightGray)){
        items(recipes) { recipe -> //aus der Liste recipes bekommt es der Reihe nach Elemente übergeben --> geh durch die Liste
            RecipeRow(recipe,
                onRecipeClick =  { recipeID ->
                    navController.navigate(Screen.RecipeScreen.withId(recipeID)) },
                onFavIconClick = { recipeID -> viewModel.toggleFavorite(recipeID)}

            )
        }
    }
}

@Composable
fun RecipeRow(recipe: Recipe,
              onRecipeClick: (Int) -> Unit,
              onFavIconClick: (Int) -> Unit = {}
) { //später werden mehrere Parameter eingefügt

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        .clickable{
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
                    contentDescription = "Favorites",
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
                Image(painter = painterResource(id = recipe.images[0]),
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



