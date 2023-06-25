package com.example.yummynotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.yummynotes.R
import com.example.yummynotes.RecipeRow
import com.example.yummynotes.models.*
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.CategoriesScreenBar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun CategoriesScreen(navController: NavController){
    val viewModel: CategoriesScreenViewModel = viewModel(
        factory = Injector.provideCategoriesScreenViewModelFactory(
            LocalContext.current
        )
    )
    Column {
        CategoriesScreenBar(
            title = stringResource(id = R.string.find_recipes),
            arrowBackClicked = { navController.popBackStack() },
            content = {
                DropdownMenuItem(onClick = {
                    val recipe = viewModel.recommendRecipe(viewModel.filteredRecipes)
                    recipe?.let {
                        val recipeID = recipe.id
                        navController.navigate(Screen.RecipeScreen.withId(recipeID))
                    }
                }
                ) {
                    Row {
                        Icon(
                             imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(id = R.string.suggest_recipe),
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.suggest_recipe), modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp).fillMaxWidth()
                        )
                    }
                }}
        )
        Spacer(modifier = Modifier.width(8.dp))

        MainContent(viewModel, navController)

    }
}

@Composable
fun MainContent(viewModel: CategoriesScreenViewModel, navController: NavController) {
    val recipe = viewModel.recipes.collectAsState()
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            //.verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(bottom = 16.dp) // Abstand zum Bildschirmende
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Horizontaler Abstand zu den Rändern
            horizontalAlignment = Alignment.Start
        ) {
            CategoryPicker(viewModel, viewModel.categories)

        }


        FilteredList(navController = navController, viewModel = viewModel)

        /*
        Spacer(modifier = Modifier.height(130.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                enabled = true,
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp) // Abstand zu den Rändern und zwischen den Buttons
            ) {
                Text(text = "finde Rezepte")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                enabled = true,
                onClick = {
                    /* TODO */
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp) // Abstand zu den Rändern und zwischen den Buttons
            ) {
                Text(text = "Rezeptempfehlung")
            }
        }*/
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryPicker(viewModel: CategoriesScreenViewModel,
                   categoryList: List<Categories>
) {
    Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary.copy(1.0f))) {
                //Spacer(modifier = Modifier.width(15.dp))
            FlowRow(modifier = Modifier
                .padding(4.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Categories.values().forEach { category ->

                    Card(modifier = Modifier
                        .padding(4.dp)
                        .clickable { viewModel.toggleCategory(category) },
                        elevation = 2.dp,
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = if (viewModel.categories.contains(category)) {
                            Color.Gray
                        } else {
                            Color.LightGray
                            }
                    ){
                        Text(modifier = Modifier.padding(12.dp,4.dp),
                            text= getLocalizedCategory(category, LocalContext.current))

                    }
                }


        }

    }}


//FILTERED LIST; CHOOSE ITEMS HERE

@Composable
fun FilteredList(
    navController: NavController,
    viewModel: CategoriesScreenViewModel) {
    val viewModel: CategoriesScreenViewModel = viewModel(factory = Injector.provideCategoriesScreenViewModelFactory(
        LocalContext.current))
    //val recipes by viewModel.recipes.collectAsState()
    val recipes = viewModel.filteredRecipes
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(modifier = Modifier.background(color = Color.LightGray)){
        items(items = recipes) { recipeItem -> //aus der Liste recipes bekommt es der Reihe nach Elemente übergeben --> geh durch die Liste

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


//FILTERED ROW; LIKE IN HOMESCREEN
//use this for picking logic: if (viewModel.categories.contains(category))

@Composable
fun FilteredRow(recipe: Recipe,
                onRecipeClick: (Int) -> Unit,
                onFavIconClick: (Recipe) -> Unit = {}
) { //später werden mehrere Parameter eingefügt
    var imageID: String = if (recipe.images.isEmpty()) {
        "android.resource://com.example.yummynotes/drawable/no_photos"
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
                    modifier = Modifier
                        .height(80.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)

            ) {
                //Log.d("HomeScreen", "URI from id: ${LocalContext.current.resourceUri(imageID)}")

                AsyncImage(model = imageID,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp))

                /*Image(painter = painterResource(id = imageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp)
                )*/
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )


            }


        }
    }}
/*
@Composable
fun FilteredsggRow(modifier: Modifier, navController: NavController, viewModel: CategoriesScreenViewModel){
    val recipes by viewModel.recipes.collectAsState()
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        ,

        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.Gray

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            //horizontalAlignment = Alignment.End
        ) {
            LazyColumn{
                items(items = recipes){ recipe ->

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("${recipes.value.t}\n${recipe.description}",
                    modifier = Modifier
                        .height(80.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)

            ) {
                //Log.d("HomeScreen", "URI from id: ${LocalContext.current.resourceUri(imageID)}")

                AsyncImage(model = imageID,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp))

                /*Image(painter = painterResource(id = imageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp)
                )*/
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )


            }


        }
    }}*/



