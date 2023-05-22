package com.example.yummynotes.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class AppBarViewModel() : ViewModel() {
    var actionIconOnClick = mutableStateOf({})

}