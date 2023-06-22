package com.example.yummynotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yummynotes.models.Recipe
import com.example.yummynotes.models.RecipeViewModel
import com.example.yummynotes.widgets.SimpleTopAppBar
import com.example.yummynotes.widgets.TopNavigationBar
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AddScreen(viewModel: RecipeViewModel, navController: NavHostController) {
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()
    Column{
        SimpleTopAppBar(
            title = "Add a Recipe",
            arrowBackClicked = { navController.popBackStack() },
            content = { /* Custom content here */ }
        )

        MainContent()

        Button(
            enabled = isButtonEnabled,
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "ADD RECIPE")
        }

    }
}

@Composable
fun MainContent() {
    var titleState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }
    var ingredientsState by remember { mutableStateOf("") }
    var instructionsState by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                value = titleState,
                onValueChange = {  titleState = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "title")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = descriptionState,
                onValueChange = { descriptionState = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "description")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = ingredientsState,
                onValueChange = { ingredientsState = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "ingredients")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = instructionsState,
                onValueChange = { instructionsState = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "instructions")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))



        }
    }
}
