package com.example.yummynotes.screens

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.navigation.Screen
import com.example.yummynotes.widgets.SimpleTopAppBar
import com.example.yummynotes.R
import com.example.yummynotes.widgets.TopNavigationBar
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun RecipeScreen(navController: NavController, viewModel: RecipeViewModel, recipeID: Int) {
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()
    val recipe = viewModel.getRecipeByID(recipeID)
    val context = LocalContext.current
    var imageID: Int = if (recipe.images.isEmpty()) {
        R.drawable.no_photos
    } else {
        recipe.images[0]
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        SimpleTopAppBar(
            title = recipe.title,
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
                    text = recipe.title,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = "Bild von ${recipe.title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                )
                Text(
                    text = "Beschreibung",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = recipe.description,
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
                    text = recipe.ingredients.replace(", ", "\n"),
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
                    text = recipe.instructions,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { viewModel.readText(context, recipe.instructions) },
                        modifier = Modifier.offset(20.dp,0.dp)
                    ) {
                        Text(text = "vorlesen")
                    }
                    Button(
                        onClick = { /* TODO: Implement delete recipe logic */ },
                        modifier = Modifier.offset(150.dp,0.dp)
                    ) {
                        Text(text = "Rezept löschen")
                    }
                }


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