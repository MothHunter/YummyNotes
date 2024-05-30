package com.example.yummynotes

//import com.example.yummynotes.models.resourceUri


import android.app.Activity
import android.graphics.fonts.Font
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.yummynotes.models.HomeScreenViewModel
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.TopNavigationBar
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController) {
    //Auf keinen fall löschen! closes app when pressing back button in HomeScreen
    //wir brauchen es für reqirement 23
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }

    val viewModel: HomeScreenViewModel = viewModel(
        factory = Injector.provideHomeScreenViewModelFactory(
            LocalContext.current
        )
    )
    Column{
        TopNavigationBar(title = stringResource(id = R.string.my_recipes), navController)
        RecipeList(viewModel = viewModel, navController = navController, modifier = Modifier)
    }
}


@Composable
fun RecipeList(
    modifier: Modifier, //evt nocch löschen
navController: NavController,
viewModel: HomeScreenViewModel) {
    val viewModel: HomeScreenViewModel = viewModel(factory = Injector.provideHomeScreenViewModelFactory(
        LocalContext.current))
    val recipesState by viewModel.recipes.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)){
        items(items = recipesState) { recipeItem -> //aus der Liste recipes bekommt es der Reihe nach Elemente übergeben --> geh durch die Liste
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
            Log.d("CheckFavStatus", "${recipeItem.title}: ${recipeItem.isFavorite}")
        }

    }
}

@Composable
fun RecipeRow(recipe: Recipe,
              onRecipeClick: (Int) -> Unit,
              onFavIconClick: (Recipe) -> Unit = {}


) { //später werden mehrere Parameter eingefügt
    var imageID: String = if (recipe.images.isEmpty()) {
        "android.resource://com.example.yummynotes/drawable/no_photo_variant2"
    } else {
        recipe.images[0]
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        .clickable
        {
            onRecipeClick(recipe.id)
        },

        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            ) {
                Column(modifier = Modifier
                    .height(80.dp)
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)) {

                    Text(
                        text= recipe.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = recipe.description

                    )

                }

                Icon(imageVector = if(recipe.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder ,
                    contentDescription = stringResource(id = R.string.favorite_recipes)
                    ,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                        onFavIconClick(recipe)
                    }
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)

            ) {

               AsyncImage(model = imageID,
                   contentDescription = null,
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(200.dp)
                       .width(200.dp))


                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )


            }


        }
    }}



