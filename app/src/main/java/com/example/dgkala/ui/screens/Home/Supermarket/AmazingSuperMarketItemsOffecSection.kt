package com.example.dgkala.ui.screens.Home.Supermarket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dgkala.Model.AmazingItems
import com.example.dgkala.Model.AmazingSuperMarketItems
import com.example.dgkala.R
import com.example.dgkala.ui.theme.dgKalaLightRed
import com.example.dgkala.ui.theme.dgKalaLightgreen

@Composable
fun AmazingSuperMarketItemsOfferSection(navController: NavController,amazingIntems:List<AmazingSuperMarketItems>){
    
    
    Column(modifier = Modifier.background(dgKalaLightgreen).fillMaxWidth()) {
        LazyRow(){
            item{
                AmazingSuperMarketItemOffreCard(topImage = R.drawable.supermarketamazings, BottomImage =R.drawable.fresh )


            }
            items(amazingIntems.size){
                AmazingSuperMarketItem(navController,amazingIntems[it])
            }
            item {
                AmazingSuperMarketItemOffreCardShowMore()
            }
        }

    }
}