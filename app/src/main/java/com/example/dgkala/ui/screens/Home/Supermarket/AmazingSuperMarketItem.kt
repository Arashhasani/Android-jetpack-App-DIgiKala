package com.example.dgkala.ui.screens.Home.Supermarket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.dgkala.Model.AmazingItems
import com.example.dgkala.Model.AmazingSuperMarketItems
import com.example.dgkala.R
import com.example.dgkala.ui.theme.dgKalaLightRed
import com.example.dgkala.ui.theme.selectedBottomBar
import ir.truelearn.digikala.util.DigitHelper

@Composable
fun AmazingSuperMarketItem(navController: NavController,item:AmazingSuperMarketItems){
    Card(modifier = Modifier
        .width(170.dp)
        .padding(vertical = 24.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(8.dp), backgroundColor = Color.White
    ) {
        var imageUrl by remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = stringResource(id = R.string.amazing_for_app), color = dgKalaLightRed, style = MaterialTheme.typography.h6, fontSize =11.sp ,fontWeight = FontWeight.SemiBold, modifier = Modifier.padding( horizontal = 8.dp, vertical = 4.dp))
            Spacer(modifier = Modifier.height(10.dp))
//            imageUrl=item.image
//            val painter= rememberAsyncImagePainter(
//                ImageRequest.Builder(LocalContext.current).data(imageUrl).apply (block = fun ImageRequest.Builder.(){scale(
//                    Scale.FILL)}).build()
//            )

            Image(painter = rememberAsyncImagePainter(model = item.image), contentDescription = "", modifier = Modifier.fillMaxWidth().height(130.dp), contentScale = ContentScale.FillBounds, alignment = Alignment.Center)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item.name.toString(), maxLines = 2, color = Color.Black, style = MaterialTheme.typography.h6, fontSize =11.sp ,fontWeight = FontWeight.SemiBold, modifier = Modifier.padding( horizontal = 8.dp).height(64.dp).fillMaxWidth(), overflow = TextOverflow.Ellipsis)

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                Image(painter = painterResource(id = R.drawable.in_stock), contentDescription = "", modifier = Modifier.size(14.dp))
                Text(text = "موجود در انبار دیجی کالا", color = selectedBottomBar, style = MaterialTheme.typography.h6, fontSize =11.sp ,fontWeight = FontWeight.SemiBold, modifier = Modifier.padding( horizontal = 8.dp))

            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                Box(modifier = Modifier
                    .width(40.dp)
                    .height(24.dp)
                    .background(dgKalaLightRed, shape = CircleShape)) {
                    Text(text = "${DigitHelper.digitByLocate("24")}%", color = Color.White, style = MaterialTheme.typography.h6, fontSize =11.sp ,fontWeight = FontWeight.SemiBold)
                }
                Row() {
                    Text(text = "تومان", color = Color.Black, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
                    Text(text = DigitHelper.digitByLocateAndSeparator("200000000"), color = Color.Black, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
                }
            }

            Text(text = DigitHelper.digitByLocateAndSeparator("2000000"), style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(10.dp))

        }




    }
}