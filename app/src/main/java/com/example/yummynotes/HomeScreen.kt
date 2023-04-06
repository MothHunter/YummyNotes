package com.example.yummynotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.getRecipes

@Composable
fun HomeScreen(navController: NavController) {

    RecipeList(getRecipes())
}

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        items(recipes) { recipe -> //aus der Liste recipes bekommt es der Reihe nach Elemente übergeben --> geh durch die Liste
            RecipeRow(recipe)
        }
    }
}

@Composable

/*
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {},
    onFavIconClick: (String) -> Unit = {}
) {
    Card(modifier = modifier
        .clickable {
            onItemClick(movie.id)
        }
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MovieImage(imageUrl = movie.images[0])
                FavoriteIcon()
            }

            MovieDetails(modifier = Modifier.padding(12.dp), movie = movie)
        }
    }
}
 */
fun RecipeRow(recipe: Recipe) { //später werden mehrere Parameter eingefügt
    Card(modifier = Modifier.fillMaxWidth().padding(20.dp)){
        Column (
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(recipe.title)
            Text(recipe.description)
            Text(recipe.instructions)
            Text(recipe.ingredients)
           //later add the row and box etc when needed
            Image(painter = painterResource(id = recipe.images[0]), contentDescription = null)


        }


    }
}
//hier können UI Elemente verschachtelt werden



