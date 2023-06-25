package com.example.yummynotes.widgets

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.getRecipes


@Composable
fun RecipeRow(
    recipe: Recipe = getRecipes()[0],
    onFavItemClicked : (Int) -> Unit ={},

) {
var toggleState by remember {
    mutableStateOf(recipe.isFavorite)
}
    Icon(
        imageVector = if (toggleState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "Favorite Movie",
        modifier = Modifier.clickable{
            onFavItemClicked(recipe.id)
        }
    )




}