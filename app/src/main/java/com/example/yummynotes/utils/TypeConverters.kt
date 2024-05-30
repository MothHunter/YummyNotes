package com.example.yummynotes.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter
import com.example.yummynotes.models.Categories
import java.io.ByteArrayOutputStream
import kotlin.streams.toList

class TypeConverters {
    @TypeConverter
    fun fromIntList(intList: List<Int>?): String? {
        if (intList == null) {
            return null
        }
        return intList.joinToString(separator = ",")
    }

    @TypeConverter
    fun toIntList(string: String?): List<Int>? {
        if (string == null || string.isEmpty()) {
            return null
        }
        return string.split(",").mapNotNull { it.toIntOrNull() }
    }
    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        if (list == null) {
            return ""
        }
        return list.joinToString(separator = "||")
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        if (string == null) {
            return listOf()
        }
        return string.split("||").toList()
    }

    @TypeConverter
    fun toBoolean(mutableStateBoolean: MutableState<Boolean>?): Boolean? {
        if (mutableStateBoolean == null) {
            return null
        }
        return mutableStateBoolean.value
    }

    @TypeConverter
    fun toMutableStateBoolean(boolean: Boolean?): MutableState<Boolean>? {
        if (boolean == null) {
            return null
        }
        return mutableStateOf(boolean)
    }

    @TypeConverter
    fun toString(list: List<Categories>?): String? {
        if (list == null) {
            return null
        }
        return list.joinToString(separator = "||")
    }

    @TypeConverter
    fun toListCategory(string: String?): List<Categories>? {
        if (string == null) {
            return null
        }
        return string.split("||").stream().map { Categories.valueOf(it) }.toList()
    }
}