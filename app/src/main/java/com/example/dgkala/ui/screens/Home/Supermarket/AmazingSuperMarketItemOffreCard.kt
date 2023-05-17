package com.example.dgkala.ui.screens.Home.Supermarket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AmazingSuperMarketItemOffreCard(topImage:Int,BottomImage:Int){
    Column(modifier = Modifier
        .width(160.dp)
        .height(375.dp)
        .padding(vertical = 8.dp, horizontal = 4.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(painter = painterResource(id = topImage), contentDescription ="",
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp))

        Spacer(modifier = Modifier.height(15.dp))
        Image(painter = painterResource(id = BottomImage), contentDescription ="",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp))
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(id = com.example.dgkala.R.string.see_all), style = MaterialTheme.typography.h6, color = Color.White, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "", tint = Color.White)

        }


    }
}