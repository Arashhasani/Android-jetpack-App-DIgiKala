package com.example.dgkala.ui.screens.Home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dgkala.DataStore.StoreDefaultLang
import com.example.dgkala.ui.theme.deSelectedBottomBar
import com.example.dgkala.ui.theme.searchBarBg
import com.example.dgkala.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.util.*


@Composable
fun SearchBarSection (){

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(65.dp)
        .background(Color.White), elevation = 5.dp) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(searchBarBg)){
            searchContent()

        }

    }
}


@Composable
private fun searchContent(){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        Icon(painter = painterResource(id = R.drawable.search), contentDescription = "", modifier = Modifier.size(24.dp))
        Text(text = stringResource(id = R.string.mydigi), modifier = Modifier.padding(start = 20.dp), textAlign = TextAlign.Center, color = deSelectedBottomBar, style = MaterialTheme.typography.h2, fontWeight = FontWeight.Normal)
        Image(painter = DigiLogoSearchBar(), contentDescription = "", modifier = Modifier
            .width(80.dp)
            .padding(start = 5.dp))
    }
}

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
private fun DigiLogoSearchBar():Painter{

    val defaultLangDataStore=StoreDefaultLang(LocalContext.current)




    return if (Locale.getDefault().getLanguage().toString()=="en"){
        painterResource(id = R.drawable.digi_red_english)
    }else{
        painterResource(id = R.drawable.digi_red_persian)
    }

}