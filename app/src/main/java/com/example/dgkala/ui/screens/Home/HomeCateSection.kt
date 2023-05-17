package com.example.dgkala.ui.screens.Home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dgkala.Model.HomeCategories
import com.example.dgkala.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeCateSection(controller: NavController,cateItems:List<HomeCategories>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = stringResource(id = R.string.category_title), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.h2, fontWeight = FontWeight.SemiBold, color = Color.Black)

        FlowRow(maxItemsInEachRow = 3, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            for (item in cateItems){

                HomeCateSectionItem(navController = controller, item = item)

            }

        }

    }

}