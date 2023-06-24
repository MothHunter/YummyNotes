package com.example.yummynotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.widgets.SimpleTopAppBar
import com.example.yummynotes.R
import com.example.yummynotes.models.*
import com.example.yummynotes.utils.Injector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@Composable
fun RecipeScreen(navController: NavController, recipeID: Int) {
    /*
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()
    */
    val viewModel: RecipeScreenViewModel = viewModel(
        factory = Injector.provideRecipeScreenViewModelFactory(
            LocalContext.current,
            recipeID = recipeID
        )
    )
    val recipe = viewModel.recipeState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    // this check is necessary because recipe WILL be a null-value after deleting
    // this is probably not the best way to do it
    if (recipe != null && recipe.value != null) {
        val imageID: Int = if (recipe.value.images.isEmpty()) {
            R.drawable.no_photos
        } else {
            recipe.value.images[0]
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            SimpleTopAppBar(
                title = recipe.value.title,
                arrowBackClicked = { navController.popBackStack() },
                content = {
                    EditButton() {
                        navController.navigate(Screen.AddScreen.withId(recipeID))
                    }
                }
            )
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = recipe.value.title,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Image(
                        painter = painterResource(id = imageID),
                        contentDescription = "Bild von ${recipe.value.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                    )
                    CategoriesList(categories = recipe.value.category)
                    Text(
                        text = "Beschreibung",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = recipe.value.description,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Zutaten",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = recipe.value.ingredients.replace(", ", "\n"),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Anleitung",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = recipe.value.instructions,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { viewModel.readText(context, recipe.value.instructions) },
                            modifier = Modifier.offset(20.dp, 0.dp)
                        ) {
                            Text(text = "vorlesen")
                        }

                        Button(
                            enabled = true,
                            onClick = {
                                navController.popBackStack()
                                coroutineScope.launch {
                                    viewModel.onDeleteButtonClick(recipeID)
                                }
                            },
                            modifier = Modifier.offset(150.dp, 0.dp)
                        ) {
                            Text(text = "Rezept löschen")
                        }
                    }


                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesList(categories: List<Categories>) {
    FlowRow(modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        categories.forEach { category ->
            Card(modifier = Modifier.padding(4.dp),
                elevation = 2.dp,
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.LightGray
            ){
                Text(modifier = Modifier.padding(12.dp,4.dp),
                    text= category.name)

            }
        }
    }
}

@Composable
fun EditButton(
    onClick: () -> Unit = {}
) {
    Icon(
        imageVector = Icons.Default.Edit,
        contentDescription = "Rezept bearbeiten",
        tint = Color.White,
        modifier = Modifier.clickable {
            onClick()
        }
    )
}