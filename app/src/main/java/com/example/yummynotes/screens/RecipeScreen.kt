package com.example.yummynotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.widgets.TopNavigationBar

@Composable
fun RecipeScreen(navController: NavController, viewModel: RecipeViewModel, recipeID: Int) {

    val recipe = viewModel.getRecipeByID(recipeID)
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(5.dp)){
        TopNavigationBar(recipe!!.title, navController)
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(text =recipe.title,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Image(painter = painterResource(id = recipe.images[0]),
                    contentDescription = "Bild von ${recipe.title}",
                    contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp))
                Text(text = "Beschreibung",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Text(text =recipe.description,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth())
                Text(text = "Zutaten",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Text(text = recipe.ingredients.replace(", ", "\n"),
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth())
                Text(text = "Anleitung",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Text(text =recipe.instructions,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth())
                Button(onClick = {
                    viewModel.readText(context ,recipe.instructions)
                }) {
                    Text(text = "read")
                }
            }
        }
    }
}