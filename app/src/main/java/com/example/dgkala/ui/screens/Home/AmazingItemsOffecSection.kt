package com.example.dgkala.ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dgkala.Model.AmazingItems
import com.example.dgkala.R
import com.example.dgkala.ui.theme.dgKalaLightRed

@Composable
fun AmazingItemsOfferSection(navController: NavController,amazingIntems:List<AmazingItems>){
    
    
    Column(modifier = Modifier.background(dgKalaLightRed).fillMaxWidth()) {
        LazyRow(){
            item{
                AmazingItemOffreCard(topImage = R.drawable.amazings, BottomImage =R.drawable.box )


            }
            items(amazingIntems.size){
                AmazingItem(navController,amazingIntems[it])
            }
            item {
                AmazingItemOffreCardShowMore()
            }
        }

    }
}