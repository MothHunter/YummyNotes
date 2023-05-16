package com.example.yummynotes.models

import com.example.yummynotes.R

data class Recipe(
    val id: Int = -1,
    val title: String = "Recipe not found!",
    val description: String = "",
    val ingredients: String = "", //später: Klasse ingredients die eine Menge übergeben bekommt
    val instructions: String = "",
    val images: List<Int> = emptyList() //noch korrigieren weil wir nicht urls haben
    //var isFavorite: Boolean = false --> das wird bei der Datenbank relevant
)

fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id = 0,
            title = "Linsensuppe",
            description = "Suppe mit Linsen",
            ingredients = "Zwiebel, Kümmel, Linsen, Suppenwürfel",
            instructions = "alles in dem Topf 19 Minuten kochen",
            images = listOf(R.drawable.linsensuppe)


        ),
        Recipe(
            id = 1,
            title = "Pizza",
            description = "Pizza mit Salami",
            ingredients = "Pizzateig, Tomatensauce, Mozerella, Salami",
            instructions = "Die Pizza bei 180 Grad in den Ofen schieben",
            images = listOf(R.drawable.pizza)
        ),
        Recipe(
            id = 2,
            title = "Curry",
            description = "Curry mit Huhn",
            ingredients = "Reis, Kokosmilch, Huhn, Mango, Zwiebel",
            instructions = "Den Reis in dem Topf Kochen. In der Pfanne die restlichen Zutaten anbraten und anschließend alles vermischen",
            images = listOf(R.drawable.curry)
        ),
    )
}