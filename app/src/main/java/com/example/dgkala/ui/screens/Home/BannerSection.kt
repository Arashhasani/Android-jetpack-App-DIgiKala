package com.example.dgkala.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.Banners

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BannerSection(navController: NavController,banners:List<Banners>){

    FlowRow(maxItemsInEachRow = 2, modifier = Modifier
        .fillMaxWidth()
        .height(290.dp)
        .padding(4.dp)) {

        for (item in banners){
            BannerItems(imglink = item)

        }


    }


}

@Composable
fun BannerItems(imglink:Banners){
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier
        .fillMaxWidth(0.5f)
        .height(140.dp)
        .padding(horizontal = 8.dp, vertical = 8.dp)) {
        Image(painter = rememberAsyncImagePainter(model = imglink.image), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

    }

}