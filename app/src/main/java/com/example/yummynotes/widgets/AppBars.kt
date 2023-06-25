package com.example.yummynotes.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp




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
        backgroundColor = Color.DarkGray,
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
                contentDescription = "Arrow back",
                tint = Color.White,
                modifier = Modifier.padding(5.dp).clickable {
                    arrowBackClicked()
                }
            )
        },
        actions = {
            androidx.compose.material.IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "recommend recipe", tint = Color.White)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                content()
            }
        }

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
        backgroundColor = Color.DarkGray,
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
                contentDescription = "Arrow back",
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
            backgroundColor = Color.DarkGray,
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