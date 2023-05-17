package com.example.dgkala.ui.screens.Home.Supermarket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dgkala.ui.theme.dgKalaLightRed

@Composable
fun AmazingSuperMarketItemOffreCardShowMore(){
    Card(modifier = Modifier
        .size(180.dp, 375.dp)
        .padding(end = 16.dp, start = 4.dp, top = 24.dp, bottom = 24.dp),
    shape = RoundedCornerShape(8.dp), backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = com.example.dgkala.R.drawable.show_more), contentDescription = "", tint =dgKalaLightRed, modifier = Modifier.size(40.dp,40.dp) )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(id = com.example.dgkala.R.string.see_all), style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Color.Black)
        }




    }
}