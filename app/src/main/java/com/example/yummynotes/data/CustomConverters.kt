package com.example.yummynotes.data

import androidx.room.TypeConverter
import com.example.yummynotes.models.Categories

class CustomConverters {
    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return list.joinToString(separator = ";")
    }
    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return list.joinToString(separator = "|")
    }
    @TypeConverter
    fun stringToIntList(string: String) : List<Int> {
        if(string.isNotEmpty()) {
            return string.split(";").map { it.toInt() }
        }
        return emptyList()
    }
    @TypeConverter
    fun stringToStringList(string: String) : List<String> {
        if(string.isNotEmpty()) {
            return string.split("|")
        }
        return emptyList()
    }
    @TypeConverter
    fun categoryListToString(list: List<Categories>) : String {
        return list.joinToString(separator = ";")
    }

    @TypeConverter
    fun stringToCategoryList(string: String) :List<Categories> {
        var catList = listOf<Categories>().toMutableList()
        if(string.isNotEmpty()) {
            val substrings = string.split(";")
            for (sub in substrings) {
                catList.add(Categories.valueOf(sub))
            }
        }
        return catList
    }
}