package com.example.yummynotes.data

import androidx.room.TypeConverter
import com.example.yummynotes.models.Categories

class CustomConverters {
    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return list.joinToString(separator = ";")
    }
    @TypeConverter
    fun stringToIntList(string: String) : List<Int> {
        return string.split(";").map { it.toInt() }
    }

    @TypeConverter
    fun categoryListToString(list: List<Categories>) : String {
        return list.joinToString(separator = ";")
    }

    @TypeConverter
    fun stringToCategoryList(string: String) :List<Categories> {
        val substrings = string.split(";")
        var catList = listOf<Categories>().toMutableList()
        for (sub in substrings) {
            catList.add(Categories.valueOf(sub))
        }
        return catList
    }
}