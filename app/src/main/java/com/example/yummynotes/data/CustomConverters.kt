package com.example.yummynotes.data

import androidx.room.TypeConverter

class CustomConverters {
    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return list.joinToString(separator = ";")
    }
    @TypeConverter
    fun stringToIntList(string: String) : List<Int> {
        return string.split(";").map { it.toInt() }
    }
}