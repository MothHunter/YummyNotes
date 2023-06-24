package com.example.yummynotes.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.yummynotes.R
import kotlinx.coroutines.delay



@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(500L) //TODO: set to higher value again for final version
        navController.navigate("main")
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(color = Color.LightGray)){
        Image(painter = painterResource(id = R.drawable.recipe_app_logo,),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}