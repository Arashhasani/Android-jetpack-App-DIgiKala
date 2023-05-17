package com.example.dgkala.ui.screens.Basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dgkala.R
import com.example.dgkala.ui.theme.dgKalaLightRed
import ir.truelearn.digikala.util.DigitHelper

@Composable
fun BasketSummary(totalPrice:String,totalCount:Int){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "خلاصه سبد", color = Color.Black, style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
            Text(text = "${totalCount.toString()} کالا ", color = Color.LightGray, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "قیمت کالا ها", color = Color.LightGray, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
            Row() {
                Text(text = "خلاصه سبد", color = Color.Black, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 4.dp))
                Image(painter = painterResource(id = R.drawable.toman), contentDescription = "", modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 4.dp))

            }
        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "قیمت کالا ها", color = Color.LightGray, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
            Row() {
                Text(text = "خلاصه سبد", color = Color.Black, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 4.dp))
                Image(painter = painterResource(id = R.drawable.toman), contentDescription = "", modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 4.dp))

            }
        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "قیمت کالا ها", color = Color.LightGray, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)
            Row() {
                Text(text = "خلاصه سبد", color = Color.Black, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 4.dp))
                Image(painter = painterResource(id = R.drawable.toman), contentDescription = "", modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 4.dp))

            }
        }
        Text(text = stringResource(id = R.string.shipping_cost_alert), color = Color.Gray, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp))

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row() {
                Image(painter = painterResource(id = R.drawable.digi_score), contentDescription = "", modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 4.dp))
                Text(text = "امتیاز دیجی کلاب", color = Color.DarkGray, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 4.dp))

            }

            Text(text = "7 امتیاز", color = Color.Black, style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 4.dp))


        }
        Text(text = stringResource(id = R.string.digiclub_description), color = Color.Gray, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp))


        Button(
            onClick = {  },
            modifier = Modifier.width(210.dp).height(60.dp).padding(horizontal = 24.dp).clip(shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = dgKalaLightRed,
                contentColor = Color.White)
        ){
            Text(text = "ادامه فرایند خرید", color = Color.White)
        }

    }

}