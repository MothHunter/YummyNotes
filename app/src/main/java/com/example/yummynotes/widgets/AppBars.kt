package com.example.yummynotes.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.yummynotes.R


// these can not be localized like the other strings because they are not in an composable
// fix??
val NavItems = listOf(
    NavItem(
        name = "Categories",
        route = "categories",
        icon = Icons.Rounded.Search,
    ),
    NavItem(
        name = "Create",
        route = "add/-1",
        icon = Icons.Rounded.Add,
    ),
    NavItem(
        name = "Favorites",
        route = "favorites",
        icon = Icons.Filled.Favorite,
    )

)



@Composable
fun CategoriesScreenBar(
    title: String,
    arrowBackClicked: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary, // hier greift man auf das colors.kt file zu
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = title, color = Color.White, modifier = Modifier.offset(80.dp,0.dp))
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.White,
                modifier = Modifier.padding(5.dp).clickable {
                    arrowBackClicked()
                }
            )
        },
        actions = {
            androidx.compose.material.IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(id = R.string.recommended_recipes), tint = Color.White)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                content()
            }
        },

        modifier = Modifier.background(MaterialTheme.colors.onPrimary)

        /*
        actions = {
            content()
        }*/
    )
}


@Composable
fun SimpleAppBar(
    title: String,
    arrowBackClicked: () -> Unit = {},
    content: @Composable () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = title, color = Color.White, modifier = Modifier.offset(80.dp,0.dp))
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.White,
                modifier = Modifier.padding(5.dp).clickable {
                    arrowBackClicked()
                }
            )
        },
        actions = {
            content()
        }
    )
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopNavigationBar(title: String, navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Column {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            title = {
                Row { Text(text = title, textAlign = TextAlign.Center, color = Color.White) }
            },
            actions = {
                Row {
                    NavItems.forEach { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route
                        IconButton(onClick = { navController.navigate(item.route) }) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            })
    }

}



//QUELLE: https://itnext.io/navigation-bar-bottom-app-bar-in-jetpack-compose-with-material-3-c57ae317bd00