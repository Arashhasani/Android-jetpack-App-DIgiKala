package com.example.dgkala.ui.screens.Basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.Card.CardItems
import com.example.dgkala.R
import com.example.dgkala.Util.LocalUtils
import com.example.dgkala.ViewModel.Cart.CartViewModel
import com.example.dgkala.ui.theme.Teal200
import com.example.dgkala.ui.theme.dgKalaLightRed
import ir.truelearn.digikala.util.DigitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun cartItem(cartitems:List<CardItems>){

    cartitems.forEach{cartitem->
        var countt by remember {
            mutableStateOf(0)
        }
        if (cartitem.count>0){
            LocalUtils.setLocate(LocalContext.current, Locale.getDefault().getLanguage())

            val context = LocalContext.current
            val cope = rememberCoroutineScope()

            val localviewmodellownder = LocalViewModelStoreOwner.current
            val lifecylleownder = LocalLifecycleOwner.current
            val cartItemViewmodel =
                localviewmodellownder?.let { ViewModelProvider(it).get(
                    CartViewModel::class.java) }
            countt=cartitem.count
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp), elevation = 1.dp) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)) {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(cartitem.image),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(90.dp),
                            contentScale = ContentScale.Fit
                        )

                        Column(modifier = Modifier
                            .fillMaxWidth(0.7f)) {
                            Text(text = cartitem.name, style = MaterialTheme.typography.h5, color = Color.Black, fontWeight = FontWeight.SemiBold)
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                            ) {
                                Image(painter = painterResource(id = R.drawable.warranty), contentDescription = "", modifier = Modifier
                                    .size(21.dp)
                                    .padding(4.dp))
                                Text(text = stringResource(id = R.string.warranty), modifier = Modifier.padding(4.dp)
                                    , style = MaterialTheme.typography.h6, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)

                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                            ) {
                                Image(painter = painterResource(id = R.drawable.store), contentDescription = "", modifier = Modifier
                                    .size(21.dp)
                                    .padding(4.dp))
                                Text(text = stringResource(id = R.string.digikala), modifier = Modifier.padding(4.dp)
                                    , style = MaterialTheme.typography.h6, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)

                            }


                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                            ) {
                                Image(painter = painterResource(id = R.drawable.in_stock), contentDescription = "", modifier = Modifier
                                    .size(21.dp)
                                    .padding(4.dp))
                                Text(text = "موجود در انبار دیجی کالا", modifier = Modifier.padding(4.dp)
                                    , style = MaterialTheme.typography.h6, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)

                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                            ) {
                                Image(painter = painterResource(id = R.drawable.k1), contentDescription = "", modifier = Modifier
                                    .size(21.dp)
                                    .padding(4.dp))
                                Text(text = stringResource(id = R.string.digikala_send), modifier = Modifier.padding(4.dp)
                                    , style = MaterialTheme.typography.h6, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)

                            }



                        }


                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 44.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .border(
                                1.dp, Color.LightGray,
                                RoundedCornerShape(8.dp)
                            )
                            .alpha(0.6f)) {

                            Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {

                                if (countt==1){
                                    Icon(Icons.Filled.Clear, contentDescription = "", tint = dgKalaLightRed, modifier = Modifier.clickable {
                                        countt--
                                        GlobalScope.launch(Dispatchers.IO) {
                                            if (cartItemViewmodel != null) {
                                                cartItemViewmodel.updateCartItem(cartitem.id,countt)
                                                cartItemViewmodel.deleteFromCart(cartitem)
                                            }
                                        }

                                    })
                                }else{
                                    Icon(painter = painterResource(id = R.drawable.ic_decrease_24), contentDescription = "", tint = dgKalaLightRed, modifier = Modifier.clickable {
                                        countt--
                                        GlobalScope.launch(Dispatchers.IO) {

                                            if (cartItemViewmodel != null) {
                                                cartItemViewmodel.updateCartItem(cartitem.id,countt)
                                            }
                                        }

                                    })
                                }


                                Text(text = DigitHelper.digitByLocateAndSeparator(countt.toString()), color = dgKalaLightRed, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 8.dp))

                                Icon(painter = painterResource(id = R.drawable.ic_increase_24), contentDescription = "", tint = dgKalaLightRed, modifier = Modifier.clickable {
                                    countt++

                                    GlobalScope.launch(Dispatchers.IO) {

                                        if (cartItemViewmodel != null) {
                                            cartItemViewmodel.updateCartItem(cartitem.id,countt)
                                        }
                                    }
                                })

                            }

                        }

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Text(text = DigitHelper.digitByLocateAndSeparator((cartitem.price*countt).toString()), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(horizontal = 8.dp))
                            Image(painter = painterResource(id = R.drawable.toman), contentDescription = "", modifier = Modifier.size(24.dp))

                        }


                    }

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {

                        if (cartitem.cartstatus.toString()=="CURRENT"){
                            Text(text = "انتقال به سبد خرید بعدی", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, color = Teal200, modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 4.dp)
                                .clickable {
                                    GlobalScope.launch(Dispatchers.IO) {
                                        val cartItemViewmodel =
                                            localviewmodellownder?.let {
                                                ViewModelProvider(it).get(
                                                    CartViewModel::class.java
                                                )
                                            }
                                        if (cartItemViewmodel != null) {
                                            cartItemViewmodel.moveToNext(cartitem.id)
                                        }
                                    }
                                })
                        }else{
                            Text(text = "حذف از این لیست", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, color = dgKalaLightRed, modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 4.dp)
                                .clickable {
                                    GlobalScope.launch(Dispatchers.IO) {
                                        val cartItemViewmodel =
                                            localviewmodellownder?.let {
                                                ViewModelProvider(it).get(
                                                    CartViewModel::class.java
                                                )
                                            }
                                        if (cartItemViewmodel != null) {
                                            cartItemViewmodel.moveToCurrent(cartitem.id)
                                        }
                                    }
                                })
                        }



                    }

                }

            }




        }



    }





}