package com.example.dgkala.ui.screens.Home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CategorySectionItem(painter: Painter,tile:String,bacgroundColor:Color=Color.Transparent,onClick:()->Unit){
    Column(modifier = Modifier
        .width(80.dp)
        .clickable { onClick() }, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(18.dp))
            .background(color = bacgroundColor)){
            Image(painter = painter, contentDescription = "", modifier = Modifier.size(52.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = tile.toString(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Color.Black)

    }
    

}