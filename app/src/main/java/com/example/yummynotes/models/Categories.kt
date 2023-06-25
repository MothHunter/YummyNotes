package com.example.yummynotes.models

import android.content.Context
import com.example.yummynotes.R

enum class Categories {
    VORSPEISEN,
    NACHSPEISEN,
    HAUPTSPEISEN,
    SNACKS,
    SALATE,
    SUPPEN,
    VEGETARISCH,
    VEGAN,
    SANDWICHES,
    SMOOTHIES,
    BACKWAREN,
    TORTEN,
    FLEISCH,
    MEDITERRAN,
    FASTFOOD
}

fun getLocalizedCategory(cat: Categories, context: Context) : String{
    when(cat){
        Categories.VORSPEISEN -> return context.getString(R.string.VORSPEISE)
        Categories.NACHSPEISEN -> return context.getString(R.string.NACHSPEISEN)
        Categories.HAUPTSPEISEN -> return context.getString(R.string.HAUPTSPEISEN)
        Categories.SNACKS -> return context.getString(R.string.SNACKS)
        Categories.SALATE -> return context.getString(R.string.SALATE)
        Categories.SUPPEN -> return context.getString(R.string.SUPPEN)
        Categories.VEGETARISCH -> return context.getString(R.string.VEGETARISCH)
        Categories.VEGAN -> return context.getString(R.string.VEGAN)
        Categories.SANDWICHES -> return context.getString(R.string.SANDWICHES)
        Categories.SMOOTHIES -> return context.getString(R.string.SMOOTHIES)
        Categories.BACKWAREN -> return context.getString(R.string.BACKWAREN)
        Categories.TORTEN -> return context.getString(R.string.TORTEN)
        Categories.FLEISCH -> return context.getString(R.string.FLEISCH)
        Categories.MEDITERRAN -> return context.getString(R.string.MEDITERRAN)
        Categories.FASTFOOD -> return context.getString(R.string.FASTFOOD)

    }
}