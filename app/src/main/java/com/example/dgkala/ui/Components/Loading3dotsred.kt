package com.example.dgkala.ui.Components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import com.example.dgkala.R

@Composable
fun Loading3dotsred(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loadingred))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )

}