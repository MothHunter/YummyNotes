package com.example.yummynotes.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.yummynotes.models.AddEditScreenViewModel
import com.example.yummynotes.ui.theme.NewPhotoPickerAndroid13Theme
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.SimpleTopAppBar
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AddScreen(navController: NavHostController, recipeID: Int) {
    val viewModel: AddEditScreenViewModel = viewModel(
        factory = Injector.provideAddEditScreenViewModelFactory(LocalContext.current,
            recipeID = recipeID))
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()

    Column{
        SimpleTopAppBar(
            title = "Add a Recipe",
            arrowBackClicked = { navController.popBackStack() },
            content = { /* Custom content here */ }
        )

        MainContent(viewModel)

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
fun MainContent(viewModel: AddEditScreenViewModel) {
    val recipe = viewModel.recipeState.collectAsState()
    var titleState by remember { mutableStateOf(viewModel.recipeState.value.title) }
    var descriptionState by remember { mutableStateOf(recipe.value.description) }
    var ingredientsState by remember { mutableStateOf(viewModel.recipeState.value.ingredients) }
    var instructionsState by remember { mutableStateOf(viewModel.recipeState.value.instructions) }

    NewPhotoPickerAndroid13Theme {
        var selectedImageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri }
        )

    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                value = viewModel.title,
                onValueChange = {  viewModel.title = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "title")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { viewModel.description = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "description")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = viewModel.ingredients,
                onValueChange = { viewModel.ingredients = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "ingredients")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = viewModel.instructions,
                onValueChange = { viewModel.instructions = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "instructions")
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))



                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }) {
                            Text(text = "Pick one photo")
                        }


                    }

                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )



            }
        }}}


