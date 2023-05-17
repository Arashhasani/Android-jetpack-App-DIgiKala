package com.example.dgkala.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.dgkala.Model.Basket.BasketProducts
import com.example.dgkala.Model.Card.CArtTypes
import com.example.dgkala.Model.Card.CardItems
import com.example.dgkala.R
import com.example.dgkala.Util.LocalUtils
import com.example.dgkala.ViewModel.Basket.BasketProductsViewModel
import com.example.dgkala.ViewModel.Cart.CartViewModel
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.Components.Loading3dotsred
import com.example.dgkala.ui.screens.Basket.BasketSummary
import com.example.dgkala.ui.screens.Basket.cartItem
import com.example.dgkala.ui.theme.*
import ir.truelearn.digikala.util.DigitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun basketScreen(navController:NavHostController){

    basket(navController)

}

@Composable
fun basket(navController: NavController){

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val tabTitles= listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list),
    )
    
    Column {
        TabRow(selectedTabIndex = selectedIndex, modifier = Modifier.padding(8.dp), contentColor = Color.Red, backgroundColor = Color.White, indicator = {line-> Box(
            modifier = Modifier
                .tabIndicatorOffset(line[selectedIndex])
                .height(3.dp)
                .background(Color.Red)


        )}) {

            tabTitles.forEachIndexed(){index,title->
                Tab(selected = selectedIndex==index, onClick = { selectedIndex=index},
                selectedContentColor = Color.Red, unselectedContentColor = Color.Gray,
                text = {
                    Row() {
                        Text(text = title, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                    }
                })

            }
            
        }
        when(selectedIndex){
            0-> shoppingCart(navController)
            1-> nextShoppingCart()
        }
    }

}
@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun shoppingCart(navController: NavController){


    LocalUtils.setLocate(LocalContext.current, Locale.getDefault().getLanguage())

    val context = LocalContext.current
    val cope = rememberCoroutineScope()

    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current

    var products by remember {
        mutableStateOf(emptyList<BasketProducts>())
    }

    var basketloading by remember {
        mutableStateOf(false)
    }
    var basketerror by remember {
        mutableStateOf("")
    }





    var cartItems by remember {
        mutableStateOf(emptyList<CardItems>())
    }

    var cartItemsloading by remember {
        mutableStateOf(false)
    }
    var cartItemserror by remember {
        mutableStateOf("")
    }



    val basketProductViewModel =
        localviewmodellownder?.let { ViewModelProvider(it).get(BasketProductsViewModel::class.java) }


    LaunchedEffect(key1 = true) {

        basketProductViewModel?.getBasketAllProducts()
    }

    LaunchedEffect(key1 = true) {
        basketProductViewModel?.items?.observe(lifecylleownder) { slides ->
            products = slides
        }
    }

    LaunchedEffect(key1 = true) {

        basketProductViewModel?.loading?.observe(lifecylleownder) { slides ->
            basketloading = slides

        }


    }

    LaunchedEffect(key1 = true) {

        basketProductViewModel?.error?.observe(lifecylleownder) { slides ->
            basketerror = slides

        }

    }
    val cartItemViewmodel =
        localviewmodellownder?.let { ViewModelProvider(it).get(CartViewModel::class.java) }!!

    LaunchedEffect(key1 = true){

        cartItemViewmodel.allitemsCurrent.collectLatest { post->
            cartItems=post
        }
    }
    LaunchedEffect(key1 = true){

        cartItemViewmodel.loading.observe(lifecylleownder) { slides ->
            cartItemsloading = slides

        }

    }

    val totalCountt=cartItemViewmodel.CURRENTtotalitemCartCount.collectAsState(initial = 0)


    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        navController.navigate(Screen.category.route) {
            popUpTo(Screen.category.route) {
                inclusive = true
            }
        }
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)




    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (cartItems.size>0){
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                    Column() {
                        Text(text = stringResource(id = R.string.your_shopping_list), style = MaterialTheme.typography.h6, color = Color.Black
                            , fontWeight = FontWeight.SemiBold)

                        Text(text = cartItems.size.toString(), style = MaterialTheme.typography.h6, color = Color.LightGray)

                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.Black)

                    }
                }
            }
            item {
                cartItem(cartitems = cartItems)
            }

            item {
                BasketSummary(totalPrice = "10000", totalCount =totalCountt.value)
            }
        }else{
           item {
               Column(modifier = Modifier
                   .fillMaxWidth()
                   .background(Color.White)
                   .padding(vertical = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                   Image(painter = painterResource(id = R.drawable.empty_cart), contentDescription = "", modifier = Modifier
                       .height(200.dp), contentScale = ContentScale.FillBounds,alignment = Alignment.Center)
                   Text(text = stringResource(id = R.string.cart_is_empty), style = MaterialTheme.typography.h6, color = Color.Gray, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
                   Spacer(modifier = Modifier
                       .height(80.dp)
                       .fillMaxWidth())
                   Spacer(modifier = Modifier
                       .height(20.dp)
                       .fillMaxWidth()
                       .background(searchBarBg))

               }
           }
        }

        if (basketerror!=""){

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "مشکل اینترنت",
                        fontFamily = irsansbold,
                        modifier = Modifier.padding(10.dp)
                    )
                    Button(
                        onClick = {
                            navController.navigate(Screen.home.route)
                            {
                                popUpTo(Screen.home.route) {
                                    inclusive = true
                                }
                            }

                        }, modifier = Modifier
                            .padding(10.dp)
                            .width(120.dp)
                            .height(40.dp), shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "تلاش مجدد",
                            fontFamily = irsans,
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }


                }
            }


        }else{
            if (basketloading){

                item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White)
                        .padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Loading3dotsred()


                    }

                }

            }else{
                item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(14.dp), horizontalAlignment = Alignment.Start) {
                        Text(text = stringResource(id = R.string.suggestion_for_you), style = MaterialTheme.typography.h5, color = Color.Black, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))

                        basketItems(products = products)


                    }
                }
            }
        }






   }
    Text(text = "shopping Cart ...")

}

@Composable
fun nextShoppingCart(){

    LocalUtils.setLocate(LocalContext.current, Locale.getDefault().getLanguage())

    val context = LocalContext.current
    val cope = rememberCoroutineScope()

    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current



    var cartItems by remember {
        mutableStateOf(emptyList<CardItems>())
    }

    var cartItemsloading by remember {
        mutableStateOf(false)
    }
    var cartItemserror by remember {
        mutableStateOf("")
    }

    val cartItemViewmodel =
        localviewmodellownder?.let { ViewModelProvider(it).get(CartViewModel::class.java) }


    LaunchedEffect(key1 = true){

        if (cartItemViewmodel != null) {
            cartItemViewmodel.allitemsNext.collectLatest { post->
                cartItems=post
            }
        }

    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 40.dp)) {
        if (cartItems.size>0){
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                    Column() {
                        Text(text = stringResource(id = R.string.your_shopping_list), style = MaterialTheme.typography.h6, color = Color.Black
                            , fontWeight = FontWeight.SemiBold)

                        Text(text = cartItems.size.toString(), style = MaterialTheme.typography.h6, color = Color.LightGray)

                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.Black)

                    }
                }
            }
            item {
                cartItem(cartitems = cartItems)
            }



        }else{
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.empty_cart), contentDescription = "", modifier = Modifier
                        .height(200.dp), contentScale = ContentScale.FillBounds,alignment = Alignment.Center)
                    Text(text = stringResource(id = R.string.cart_is_empty), style = MaterialTheme.typography.h6, color = Color.Gray, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth())


                }
            }
        }







    }



}


@OptIn(ExperimentalLayoutApi::class, DelicateCoroutinesApi::class)
@Composable
fun basketItems(products:kotlin.collections.List<BasketProducts>){

    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current


    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,

    ) {
       for (item in products){
           Card(
               modifier = Modifier
                   .fillMaxWidth(0.5f)
                   .padding(1.dp), elevation = 0.dp
           ) {

               Column(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(vertical = 8.dp)
               ) {

                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(vertical = 4.dp)
                   ) {

                       Box {
                           Image(
                               painter = rememberAsyncImagePainter(item.image),
                               contentDescription = "",
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(130.dp),
                               contentScale = ContentScale.Fit
                           )

                           Column(
                               modifier = Modifier
                                   .align(Alignment.BottomStart)
                                   .fillMaxWidth()
                                   .padding(8.dp),

                               horizontalAlignment = Alignment.Start,
                               verticalArrangement = Arrangement.Center
                           ) {


                               Surface(
                                   modifier = Modifier
                                       .padding(4.dp)
                                       .size(26.dp)
                                       .clip(CircleShape)
                                       .border(1.dp, dgKalaLightRed, CircleShape)

                               ) {

                                   IconButton(onClick = {

                                       kotlinx.coroutines.GlobalScope.launch{
                                           val cartItemViewmodel = localviewmodellownder?.let {
                                               ViewModelProvider(
                                                   it
                                               ).get(CartViewModel::class.java)
                                           }

                                           if (cartItemViewmodel != null) {
                                               cartItemViewmodel.addPost(
                                                   CardItems(
                                                       item._id,item.discountPercent, item.image,
                                                       item.name,
                                                       item.price,
                                                       item.seller,
                                                       1,
                                                       cartstatus = CArtTypes.CURRENT

                                                   )
                                               )
                                               cartItemViewmodel.allitemsCurrent.collectLatest {items->
                                                   for (itemm in items){
                                                       Log.e("cart",itemm.toString())
                                                   }

                                               }
                                           }


                                       }



                                   }) {
                                       Icon(
                                           imageVector = Icons.Default.Add,
                                           contentDescription = "",
                                           tint = dgKalaLightRed,

                                           )
                                   }

                               }


                           }
                       }


                   }

                   Spacer(modifier = Modifier.height(10.dp))

                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(vertical = 8.dp)
                   ) {

                       Text(
                           text = item.name,
                           modifier = Modifier
                               .fillMaxSize()
                               .height(48.dp)
                               .padding(horizontal = 8.dp),
                           style = MaterialTheme.typography.h6,
                           fontWeight = FontWeight.SemiBold,
                           color = Color.Black,
                           maxLines = 2,
                           overflow = TextOverflow.Ellipsis
                       )

                       Spacer(modifier = Modifier.height(10.dp))

                       Row(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(start = 4.dp),
                           horizontalArrangement = Arrangement.Start,
                           verticalAlignment = Alignment.CenterVertically
                       ) {

                           Icon(
                               painter = painterResource(id = R.drawable.in_stock),
                               contentDescription = "",
                               modifier = Modifier
                                   .size(22.dp)
                                   .padding(2.dp),
                               tint = Teal200
                           )
                           Text(
                               text = item.seller,
                               style = MaterialTheme.typography.h6,
                               fontWeight = FontWeight.SemiBold,
                               color = Color.DarkGray,
                           )

                       }

                       Spacer(modifier = Modifier.height(10.dp))


                       Row(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(4.dp),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.Top
                       ) {

                           Box(
                               modifier = Modifier
                                   .width(40.dp)
                                   .height(24.dp)
                                   .background(
                                       color = dgKalaLightRed,
                                       shape = CircleShape
                                   )
                                   .wrapContentWidth(Alignment.CenterHorizontally)
                                   .wrapContentHeight(Alignment.CenterVertically)
                           ) {
                               Text(
                                   text = "${DigitHelper.digitByLocateAndSeparator(item.discountPercent.toString())}%",
                                   color = Color.White,
                                   style = MaterialTheme.typography.h6,
                                   fontWeight = FontWeight.Bold,
                               )
                           }


                           Column {

                               Row {
                                   Text(
                                       text = DigitHelper.digitByLocateAndSeparator(
                                           DigitHelper.applyDiscount(item.price.toLong(), item.discountPercent)
                                               .toString()
                                       ),
                                       style = MaterialTheme.typography.body2,
                                       fontWeight = FontWeight.SemiBold,
                                   )

//                                   Icon(
//                                       painter = currencyLogoChangeByLanguage(),
//                                       contentDescription = "",
//                                       modifier = Modifier
//                                           .size(MaterialTheme.spacing.semiLarge)
//                                           .padding(horizontal = MaterialTheme.spacing.extraSmall)
//                                   )

                               }

                               Text(
                                   text = DigitHelper.digitByLocateAndSeparator(item.price.toString()),
                                   color = Color.LightGray,
                                   style = MaterialTheme.typography.body2,
                                   textDecoration = TextDecoration.LineThrough
                               )
                           }

                       }


                   }


               }


           }
       }


    }
   


}