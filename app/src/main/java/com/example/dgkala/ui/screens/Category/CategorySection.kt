package com.example.dgkala.ui.screens.Category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.Category.SubCategory
import com.example.dgkala.ui.theme.Teal200
import com.example.dgkala.ui.theme.deSelectedBottomBar
import com.example.dgkala.ui.theme.moreBg
import ir.truelearn.digikala.util.DigitHelper

@Composable
fun CategorySection(navController: NavController ,title:String,itemlist:List<SubCategory>){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 11.dp, end = 11.dp, top = 11.dp, bottom = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = title.toString(), style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, color = Color.Black)
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "مشاهده همه", style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Teal200)
        }

    }

    LazyRow(){
        items(itemlist.size){
            catItems(item = itemlist[it])
        }
    }

}

@Composable
fun catItems(item : SubCategory){

    Card(modifier = Modifier
        .height(210.dp)
        .width(130.dp)
        .padding(horizontal = 4.dp, vertical = 8.dp), backgroundColor = moreBg, elevation = 0.dp, shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = rememberAsyncImagePainter(model = item.image), contentDescription = "", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 11.dp, horizontal = 8.dp)
                .height(90.dp), contentScale = ContentScale.FillBounds,alignment = Alignment.Center)

            Text(text = item.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Color.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "  ${DigitHelper.digitByLocate(item.count.toString())} کالا ", style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Color.LightGray, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 10.dp))

        }



    }

}