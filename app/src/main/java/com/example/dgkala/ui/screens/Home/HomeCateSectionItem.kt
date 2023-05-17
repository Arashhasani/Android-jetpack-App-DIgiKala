package com.example.dgkala.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.HomeCategories

@Composable
fun HomeCateSectionItem(navController: NavController,item:HomeCategories){
    Column(modifier = Modifier.size(100.dp,160.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Image(painter = rememberAsyncImagePainter(model = item.image), contentDescription = "", modifier = Modifier
            .size(100.dp, 100.dp)
            .padding(vertical = 4.dp))
        Text(text = item.name.toString(), style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 4.dp).fillMaxWidth(), textAlign = TextAlign.Center, color = Color.Black
        , maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
    
}