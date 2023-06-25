package com.example.yummynotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yummynotes.RecipeList
import com.example.yummynotes.models.*
import com.example.yummynotes.utils.Injector
import com.example.yummynotes.widgets.SimpleTopAppBar
import com.example.yummynotes.widgets.TopNavigationBar
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
        SimpleTopAppBar(
            title = "Finde Rezepte",
            arrowBackClicked = { navController.popBackStack() },
            content = { /* Custom content here */ }
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
            .verticalScroll(rememberScrollState())
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
        Spacer(modifier = Modifier.height(130.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                enabled = true,
                onClick = {
                    /* TODO */
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
        }
    }
}




@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryPicker(viewModel: CategoriesScreenViewModel,
                   categoryList: List<Categories>
) {
    Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary.copy(1.0f))) {
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
                            text= category.name)

                    }
                }


        }

    }}



