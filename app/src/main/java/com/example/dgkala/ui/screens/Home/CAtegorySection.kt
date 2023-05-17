package com.example.dgkala.ui.screens.Home

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.dgkala.R
import com.example.dgkala.Util.Constants.AUCTION_URL
import com.example.dgkala.Util.Constants.DIGIJET_URL
import com.example.dgkala.Util.Constants.DIGIPAY_URL
import com.example.dgkala.Util.Constants.DIGIPLUS_URL
import com.example.dgkala.Util.Constants.GIFT_CARD_URL
import com.example.dgkala.Util.Constants.MORE_URL
import com.example.dgkala.Util.Constants.PINDO_URL
import com.example.dgkala.Util.Constants.SHOPPING_URL
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.theme.amber
import com.example.dgkala.ui.theme.moreBg

@Composable
fun CategorySection(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 18.dp)) {
        
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            CategorySectionItem(painter = painterResource(id = R.drawable.digijet), tile = stringResource(
                id = R.string.digi_jet), onClick = onClickFunAction(navController = navController, url = DIGIJET_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.auction), tile = stringResource(
                id = R.string.digi_style), onClick = onClickFunAction(navController = navController, url = AUCTION_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.digipay), tile = stringResource(
                id = R.string.digi_pay), onClick = onClickFunAction(navController = navController, url = DIGIPAY_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.pindo),tile = stringResource(
                id = R.string.pindo), onClick = onClickFunAction(navController = navController, url = PINDO_URL) , bacgroundColor = amber)


        }



        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            CategorySectionItem(painter = painterResource(id = R.drawable.giftcard), tile = stringResource(
                id = R.string.gift_card), onClick = onClickFunAction(navController = navController, url = GIFT_CARD_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.digiplus), tile = stringResource(
                id = R.string.digi_plus), onClick = onClickFunAction(navController = navController, url = DIGIPLUS_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.shopping), tile = stringResource(
                id = R.string.digi_shopping), onClick = onClickFunAction(navController = navController, url = SHOPPING_URL) )

            CategorySectionItem(painter = painterResource(id = R.drawable.more), tile = stringResource(
                id = R.string.more), onClick =onClickFunAction(navController = navController, url = MORE_URL) , bacgroundColor = moreBg)


        }
        
    }
    
    
}

@Composable
fun onClickFunAction(navController: NavController,url:String):()->Unit={
    navController.navigate(Screen.webView.route+"?url=${url}")
}


