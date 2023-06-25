package com.example.yummynotes.models

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yummynotes.R
import com.example.yummynotes.models.Categories
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


const val NEW_RECIPE = -1

@Serializable
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
    val images: List<String> = emptyList(),
    val category: List<Categories> = listOf(),
    var isFavorite: Boolean = false // --,> das wird bei der Datenbank relevant
){

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

fun recipeToJson (recipe: Recipe) : String {
    return Json.encodeToString(recipe.copy(images = emptyList()))
}

fun jsonToRecipe (jsonString: String) : Recipe {
    return Json.decodeFromString<Recipe>(jsonString)

}

// Usage in Composable:
// val imageUri = LocalContext.current.resourceUri(imageID)
/*
fun Context.resourceUri(resourceId: Int): Uri = with(resources) {
    Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(getResourcePackageName(resourceId))
        .appendPath(getResourceTypeName(resourceId))
        .appendPath(getResourceEntryName(resourceId))
        .build()
}
*/

fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            title = "Linsensuppe",
            description = "Suppe mit Linsen, Zwiebeln und Karotten",
            ingredients = "250 g getrocknete braune oder grüne Linsen\n" +
                    "1 Zwiebel, gehackt\n" +
                    "2 Karotten, gewürfelt\n" +
                    "2 Knoblauchzehen, gehackt\n" +
                    "1 Liter Gemüsebrühe\n" +
                    "Salz und Pfeffer nach Geschmack\n" +
                    "2 Esslöffel Olivenöl",
            instructions = "Die Linsen gründlich abspülen und abtropfen lassen. In einem großen Topf das Olivenöl erhitzen und die Zwiebel, Karotten und Knoblauch darin anbraten, bis sie leicht gebräunt sind. Die abgetropften Linsen hinzufügen und kurz mit anbraten. Die Gemüsebrühe in den Topf gießen und zum Kochen bringen. Die Hitze reduzieren und die Suppe etwa 30-40 Minuten köcheln lassen, bis die Linsen weich sind. Mit Salz und Pfeffer abschmecken und gegebenenfalls nachwürzen. Die Linsensuppe heiß servieren.",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.SUPPEN, Categories.VEGETARISCH, Categories.VEGAN),
            images = listOf("android.resource://com.example.yummynotes/drawable/linsensuppe")


        ),
        Recipe(
            title = "Pizza",
            description = "Schnelles Rezept für eine Pizza mit Salami und Käse",
            ingredients = "1 fertiger Pizzateig (z.B. aus dem Supermarkt)\n" +
                    "Tomatensauce\n" +
                    "Mozzarella-Käse\n" +
                    "Salami",
            instructions = "Den fertigen Pizzateig aus der Verpackung nehmen und auf einer leicht bemehlten Oberfläche ausrollen. Den Backofen gemäß den Anweisungen auf der Verpackung des Pizzateigs vorheizen. Die Tomatensauce gleichmäßig auf dem ausgerollten Pizzateig verteilen, dabei einen kleinen Rand frei lassen. Den Mozzarella-Käse in Scheiben schneiden oder zupfen und auf der Sauce verteilen. Die Salami hinzufügen. Nach Belieben Kräuter und Gewürze wie Oregano, Basilikum oder Knoblauchpulver über den Belag streuen. Den belegten Pizzateig auf ein mit Backpapier ausgelegtes Backblech oder eine Pizzapfanne legen. Den Pizzateig gemäß den Anweisungen auf der Verpackung des Pizzateigs backen, in der Regel bei einer bestimmten Temperatur für eine bestimmte Zeit.",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.MEDITERRAN, Categories.FLEISCH),
            images = listOf("android.resource://com.example.yummynotes/drawable/pizza")
        ),
        Recipe(
            title = "Curry",
            description = "Ein köstliches Currygericht mit Hühnerfleisch, aromatischen Gewürzen und einer cremigen Sauce.",
            ingredients = "500 g Hühnerfleisch (gewürfelt oder in Streifen geschnitten)\n" +
                    "2 Zwiebeln (fein gehackt)\n" +
                    "3 Knoblauchzehen (gepresst)\n" +
                    "1 Stück Mango\n" +
                    "400 g Reis\n" +
                    "1 Stück Ingwer (ca. 2 cm gerieben)\n" +
                    "2 Paprika (in Streifen geschnitten)\n" +
                    "400 ml Kokosmilch\n" +
                    "2 EL Currypulver\n" +
                    "1 TL Kurkuma\n" +
                    "1 TL Kreuzkümmel",
            instructions = "Das Hühnerfleisch in einer Pfanne mit etwas Öl anbraten, bis es goldbraun ist. Anschließend aus der Pfanne nehmen und beiseite stellen. In derselben Pfanne die fein gehackten Zwiebeln und den gepressten Knoblauch anbraten, bis sie goldbraun sind und ein angenehmer Duft entsteht. Die Mango schälen, entkernen und in Würfel schneiden. Den Reis nach Packungsanweisung kochen und beiseite stellen. Den geriebenen Ingwer sowie die Paprikastreifen zur Zwiebel-Knoblauch-Mischung geben und für weitere 2-3 Minuten braten, bis die Paprika leicht weich ist. Das Currypulver, Kurkuma und Kreuzkümmel hinzufügen. Gut umrühren, um die Gewürze gleichmäßig zu verteilen. Das angebratene Hühnerfleisch und die Mango-Würfel in die Pfanne geben und gut vermischen. Die Kokosmilch hinzufügen und zum Kochen bringen. Die Hitze reduzieren und das Curry etwa 15-20 Minuten köcheln lassen, bis das Hühnerfleisch zart ist und die Sauce eingedickt ist. Das Curry mit Salz abschmecken und gegebenenfalls nachwürzen. Das Curry mit Hühnerfleisch und Mango servieren, zusammen mit dem gekochten Reis.",
            isFavorite = false,
            category = listOf(Categories.SNACKS, Categories.NACHSPEISEN),
            images = listOf("android.resource://com.example.yummynotes/drawable/curry")
        ),
        Recipe(
            title = "Pfannkuchen",
            description = "Pfannkuchen nach amerikanischer Art",
            ingredients = " 200 g Mehl\n" +
                    "2 Eier\n" +
                    "300 ml Milch\n" +
                    "1 EL Zucker\n" +
                    "1 TL Backpulver\n" +
                    "Prise Salz\n" +
                    "Butter oder Öl zum Braten",
            instructions = "In einer großen Schüssel das Mehl, den Zucker, das Backpulver und das Salz vermischen. In einer separaten Schüssel die Eier aufschlagen und leicht verquirlen. Die Milch hinzufügen und gut vermischen. Die Ei-Milch-Mischung langsam zu den trockenen Zutaten geben und mit einem Schneebesen zu einem glatten Teig verrühren. Dabei darauf achten, keine Klumpen zu hinterlassen. Eine Pfanne bei mittlerer Hitze erhitzen und etwas Butter oder Öl darin schmelzen lassen. Eine kleine Kelle des Teigs in die Pfanne geben und ihn gleichmäßig verteilen, um einen runden Pfannkuchen zu formen. Den Pfannkuchen von beiden Seiten goldbraun backen, bis er aufgegangen und leicht knusprig ist. Den fertigen Pfannkuchen auf einen Teller legen und warm halten. Den Vorgang mit dem restlichen Teig wiederholen, bis alle Pfannkuchen gebacken sind. Die Pfannkuchen nach Belieben mit Toppings wie Ahornsirup, Früchten, Schokoladensauce oder herzhaften Füllungen servieren und genießen.",
            isFavorite = false,
            category = listOf(Categories.HAUPTSPEISEN, Categories.FLEISCH),
            images = listOf("android.resource://com.example.yummynotes/drawable/pfannkuchen")
        ),
        Recipe(
            title = "Mozarellaplatte",
            description = "Mazarella mit geschnittenen Tomaten, Olivenöl und Basilikum",
            ingredients = "2-3 frische Mozzarellakugeln\n" +
                    "2-3 reife Tomaten\n" +
                    "Frisches Basilikum\n" +
                    "Olivenöl\n" +
                    "Balsamico-Glace\n" +
                    "Salz und Pfeffer nach Geschmack",
            instructions = "Die frischen Mozzarellakugeln in Scheiben schneiden. Die Tomaten ebenfalls in Scheiben schneiden. Auf einem Servierteller abwechselnd Mozzarella- und Tomatenscheiben anordnen, dabei darauf achten, dass die Farben schön abwechseln. Die Basilikumblätter abzupfen und über die Mozzarella-Tomaten-Scheiben streuen. Die Mozarellaplatte leicht mit Salz und Pfeffer würzen. Etwas Olivenöl über die Scheiben träufeln und mit einem Hauch von Balsamico-Glace garnieren. Die Mozarellaplatte servieren und genießen.",
            isFavorite = false,
            category = listOf(Categories.VORSPEISEN, Categories.MEDITERRAN, Categories.VEGETARISCH),
            images = listOf("android.resource://com.example.yummynotes/drawable/mozarellaplatte")
        ),
    )
}