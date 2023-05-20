package com.example.yummynotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yummynotes.RecipeList
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.widgets.TopNavigationBar
import kotlinx.coroutines.launch

@Composable
fun EditAndAddScreen(viewModel: RecipeViewModel, navController: NavHostController, recipeID: Int) {
    var recipe: Recipe
    val coroutineScope = rememberCoroutineScope()
    if (recipeID != -1){
        recipe = viewModel.getRecipeByID(recipeID)

    }

    //val recipe = viewModel.getRecipeByID(recipeID)
    Column{
        TopNavigationBar("Edit or Add", navController)
    }
    @Composable
    fun MainContent(recipe: Recipe){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = recipe.title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {

                })
        }}}

        /*

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        OutlinedTextField(
            value = title,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                title = it
                isTitleEmpty = it.isEmpty()
            },
            label = {
                Text(text = stringResource(R.string.enter_movie_title))
            },
            isError = isTitleEmpty,
        )
        if(isTitleEmpty) {
            Text(
                text = stringResource(R.string.enter_movie_title_required),
                color = Color.Red
            )
        }

        OutlinedTextField(
            value = year,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                year = it
                isYearEmpty = it.isEmpty()
            },
            label = { Text(stringResource(R.string.enter_movie_year)) },
            isError = isYearEmpty
        )
        if(isYearEmpty) {
            Text(
                text = stringResource(R.string.enter_movie_year_required),
                color = Color.Red
            )
        }

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.select_genres),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h6
        )

        LazyHorizontalGrid(
            modifier = Modifier.height(100.dp),
            rows = GridCells.Fixed(3)){
            items(genreItems.size) { index ->
                val genreItem = genreItems[index]
                Chip(
                    modifier = Modifier.padding(2.dp),
                    colors = ChipDefaults.chipColors(
                        backgroundColor = if (genreItem.isSelected)
                            colorResource(id = R.color.purple_200)
                        else
                            colorResource(id = R.color.white)
                    ),
                    onClick = {
                        genreItems = genreItems.map {
                            if (it.title == genreItem.title) {
                                genreItem.copy(isSelected = !genreItem.isSelected)
                            } else {
                                it
                            }
                        }
                    }
                ) {
                    Text(text = genreItem.title.name)
                }
            }
        }

        OutlinedTextField(
            value = director,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                director = it
                isDirectorEmpty = it.isEmpty()
            },
            label = { Text(stringResource(R.string.enter_director)) },
            isError = isDirectorEmpty,
        )
        if(isDirectorEmpty) {
            Text(
                text = stringResource(R.string.enter_movie_director_required),
                color = Color.Red
            )
        }

        OutlinedTextField(
            value = actors,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                actors = it
                isActorsEmpty = it.isEmpty()
            },
            label = { Text(stringResource(R.string.enter_actors)) },
            isError = isActorsEmpty
        )
        if(isActorsEmpty) {
            Text(
                text = stringResource(R.string.enter_movie_actors_required),
                color = Color.Red
            )
        }

        OutlinedTextField(
            value = plot,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            onValueChange = { plot = it },
            label = {
                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.enter_plot)
                )
            },
            isError = false
        )

        OutlinedTextField(
            value = rating,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                rating = if (it.startsWith("0")) {
                    ""
                } else {
                    it
                }
                isRatingEmpty = it.isEmpty()
            },
            label = { Text(stringResource(R.string.enter_rating)) },
            isError = isRatingEmpty
        )
        if(isRatingEmpty) {
            Text(
                text = stringResource(R.string.enter_movie_rating_required),
                color = Color.Red
            )
        }

        isEnabledSaveButton = addMovieViewModel.isValidMovie(title, year, genreItems.filter { x -> x.isSelected }.map { it.title }.toString(), director, actors, rating.toFloatOrNull() ?: 0.0f)

        Button(
            enabled = isEnabledSaveButton,
            onClick = {
                coroutineScope.launch {
                    addMovieViewModel.addMovie(Movie(title = title, year = year, genre = genreItems.filter { x -> x.isSelected }.map { it.title }.toString(), director = director, actors = actors, plot = plot, images = listOf("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"), rating = rating.toFloatOrNull() ?: 0.0f))
                }
                navController.navigate(Screen.HomeScreen.route)
            }) {
            Text(text = stringResource(R.string.add))
        }
    }
}
}
}*/