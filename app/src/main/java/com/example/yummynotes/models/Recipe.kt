package com.example.yummynotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yummynotes.R

const val NEW_RECIPE = -1
@Entity
data class Recipe(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    var title: String = "",
    var description: String = "",
    var ingredients: String = "", //später: Klasse ingredients die eine Menge übergeben bekommt
    var instructions: String = "",
    // TODO: image references need to be changed from Int to URI everywhere
    //  to be able to add new images!!
    val images: List<Int> = emptyList(), //noch korrigieren weil wir nicht urls haben
    val category: List<Categories> = listOf(),
    var isFavorite: Boolean = false // --,> das wird bei der Datenbank relevant
){

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}


fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            title = "Linsensuppe",
            description = "Suppe mit Linsen",
            ingredients = "Zwiebel, Kümmel, Linsen, Suppenwürfel",
            instructions = "alles in dem Topf 19 Minuten kochen",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.SUPPEN, Categories.VEGETARISCH),
            images = listOf(R.drawable.linsensuppe)


        ),
        Recipe(
            title = "Pizza",
            description = "Pizza mit Salami",
            ingredients = "Pizzateig, Tomatensauce, Mozerella, Salami",
            instructions = "Die Pizza bei 180 Grad in den Ofen schieben",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.MEDITERRAN, Categories.FLEISCH),
            images = listOf(R.drawable.pizza)
        ),
        Recipe(
            title = "Curry",
            description = "Curry mit Huhn",
            ingredients = "Reis, Kokosmilch, Huhn, Mango, Zwiebel",
            instructions = "Den Reis in dem Topf Kochen. In der Pfanne die restlichen Zutaten anbraten und anschließend alles vermischen",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.FLEISCH),
            images = listOf(R.drawable.curry)
        ),
        Recipe(
            title = "Curry",
            description = "Curry mit Huhn",
            ingredients = "Reis, Kokosmilch, Huhn, Mango, Zwiebel",
            instructions = "Den Reis in dem Topf Kochen. In der Pfanne die restlichen Zutaten anbraten und anschließend alles vermischen",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.FLEISCH),
            images = listOf(R.drawable.curry)
        ),
        Recipe(
            title = "Curry",
            description = "Curry mit Huhn",
            ingredients = "Reis, Kokosmilch, Huhn, Mango, Zwiebel",
            instructions = "Den Reis in dem Topf Kochen. In der Pfanne die restlichen Zutaten anbraten und anschließend alles vermischen",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.FLEISCH),
            images = listOf(R.drawable.curry)
        ),
    )
}