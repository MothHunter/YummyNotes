package com.example.yummynotes.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AddAndEditScreenViewModel() : ViewModel() {
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var ingredients = mutableStateOf("")
    var instructions = mutableStateOf("")
    var images = MutableStateFlow(listOf<Int>())
/*
    fun onTextChange(title: String){
        this.title.value = title
    }*/

    //we do not need a viewmodel here anymore???

}