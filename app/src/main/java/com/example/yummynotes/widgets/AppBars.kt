package com.example.yummynotes.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.style.TextAlign


val NavItems = listOf(
    NavItem(
        name = "Search",
        route = "search",
        icon = Icons.Rounded.Search,
    ),
    NavItem(
        name = "Create",
        route = "add",
        icon = Icons.Rounded.Add,
    )
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopNavigationBar(title: String, navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Column {
        TopAppBar(
            title = {
                Row { Text(text = title, textAlign = TextAlign.Center) }
            },
            actions = {
                Row {
                    NavItems.forEach { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route
                        IconButton(onClick = { navController.navigate(item.route) }) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon"
                            )
                        }
                    }
                }
            })
    }

}



//QUELLE: https://itnext.io/navigation-bar-bottom-app-bar-in-jetpack-compose-with-material-3-c57ae317bd00