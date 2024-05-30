package com.example.yummynotes.utils

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import com.example.yummynotes.models.Recipe


// TODO: implement save/load for recipe-JSON files
//  seems to be complicated by change in file access with Android 10/11
// Source: https://stackoverflow.com/a/56468733
fun exportRecipe(recipe: Recipe, context: Context) {
    val resolver = context.contentResolver
    val values = ContentValues()
    // save to a folder
    values.put(MediaStore.MediaColumns.DISPLAY_NAME, recipe.title)
    values.put(MediaStore.MediaColumns.MIME_TYPE, "yummyNotes/ynjs")
    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    val uri = resolver.insert(MediaStore.Files.getContentUri("external"), values)
// You can use this outputStream to write whatever file you want:
    val outputStream = resolver.openOutputStream(uri!!)
// Or alternatively call other methods of ContentResolver, e.g. .openFile

}