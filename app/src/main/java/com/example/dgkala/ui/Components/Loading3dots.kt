package com.example.dgkala.ui.Components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import com.example.dgkala.R

@Composable
fun Loading3dots(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading3dots))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )

}