package com.example.yummynotes.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
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
import com.example.yummynotes.models.NEW_RECIPE
import com.example.yummynotes.ui.theme.NewPhotoPickerAndroid13Theme
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.SimpleTopAppBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun AddScreen(navController: NavHostController, recipeID: Int) {
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()
    val viewModel: AddEditScreenViewModel = viewModel(
        factory = Injector.provideAddEditScreenViewModelFactory(
            LocalContext.current,
            recipeID = recipeID
        )
    )

    Column {
        SimpleTopAppBar(
            title = if (recipeID == NEW_RECIPE) {
                "Rezept hinzufügen"
            } else {
                "Rezept bearbeiten"
            },
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
            Text(text = "REZEPT HINZUFÜGEN")
        }

    }
}

@Composable
fun MainContent(viewModel: AddEditScreenViewModel) {
    val recipe = viewModel.recipeState.collectAsState()
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()

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
                    onValueChange = { viewModel.title = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Titel")
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Beschreibung")
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = viewModel.ingredients,
                    onValueChange = { viewModel.ingredients = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Zutaten")
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = viewModel.instructions,
                    onValueChange = { viewModel.instructions = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Anleitung")
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
                        Text(text = "Wähle ein Bild")
                    }


                }
                Box(
                    modifier = Modifier
                        .size(130.dp, 100.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }



                Button(
                    enabled = viewModel.buttonEnabled,
                    onClick = {
                        coroutineScope.launch {
                            viewModel.onAddEditButtonClick()
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (viewModel.recipeID == NEW_RECIPE) {
                            "REZEPT HINZUFÜGEN"
                        } else {
                            "ÄNDERUNGEN SPEICHERN"
                        }
                    )
                }
            }
        }
    }
}


