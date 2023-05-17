package com.example.dgkala.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.HomeCenterBanners

@Composable
fun CenterBanerSection(navController: NavController,item:HomeCenterBanners){

    Card(modifier = Modifier
        .fillMaxWidth().height(170.dp)
        .padding(8.dp), shape = RoundedCornerShape(8.dp)) {
        Image(painter = rememberAsyncImagePainter(model = item.image), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)


    }

}