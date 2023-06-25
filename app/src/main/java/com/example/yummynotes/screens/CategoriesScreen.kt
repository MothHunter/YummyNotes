package com.example.yummynotes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        MainContent(viewModel, navController)
    }
}
@Composable
fun MainContent(viewModel : CategoriesScreenViewModel, navController : NavController){
    val recipe = viewModel.recipes.collectAsState()
    val isButtonEnabledFlow = remember { MutableStateFlow(false) }
    val isButtonEnabled by isButtonEnabledFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            CategoryPicker(viewModel, viewModel.categories)
        }
    }




}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryPicker(viewModel: CategoriesScreenViewModel,
                   categoryList: List<Categories>
) {
    FlowRow(modifier = Modifier.padding(4.dp),
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
                    Color.LightGray}
            ){
                Text(modifier = Modifier.padding(12.dp,4.dp),
                    text= category.name)

            }
        }
    }
}


