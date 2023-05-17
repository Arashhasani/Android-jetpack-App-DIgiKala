package com.example.dgkala.ui.screens

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.example.dgkala.DataStore.StoreLoginState
import com.example.dgkala.DataStore.StoreTokenInfo
import com.example.dgkala.DataStore.StoreUserInfo
import com.example.dgkala.R
import com.example.dgkala.ViewModel.HomeSliderViewModel
import com.example.dgkala.ViewModel.Profile.LoginViewModel
import com.example.dgkala.ui.screens.Login.MyButton
import com.example.dgkala.ui.screens.Login.MyEditText
import com.example.dgkala.ui.screens.Login.MyLoadingButton
import com.example.dgkala.ui.theme.searchBarBg
import ir.truelearn.digikala.util.DigitHelper
import kotlinx.coroutines.*


@Composable
fun profileScreen(navController: NavHostController) {
    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current
    val defaultLoginState = StoreLoginState(LocalContext.current)

    var profileStatee by remember {
        mutableStateOf(0)
    }

    val default_Logins = defaultLoginState.getLoginState.collectAsState("")
    if (default_Logins.value == "1") {
        Profile(navController = navController)
    } else {
        Login(navController = navController)

    }


}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Profile(navController: NavHostController) {

    val defaultLoginState = StoreLoginState(LocalContext.current)
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    Icons.Filled.Close, contentDescription = "", modifier = Modifier
                        .padding(
                            vertical = 11.dp, horizontal = 11.dp
                        )
                        .size(24.dp), tint = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.digi_settings),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            vertical = 11.dp, horizontal = 11.dp
                        )
                        .size(24.dp),
                    tint = Color.Black
                )

            }
        }

        item {
            Spacer(modifier = Modifier.height(44.dp))
        }

        item {
            Text(
                text = "نام یوزر",
                color = Color.Black,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = DigitHelper.digitByLocate("09121234567"),
                color = Color.Gray,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 34.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.digi_score),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.digikala_score),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                }


                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(80.dp)
                        .alpha(0.2f), color = Color.LightGray
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.digi_wallet),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.digikala_wallet_active),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                }


            }
        }

        item {
            Spacer(modifier = Modifier
                .height(10.dp)
                .background(searchBarBg)
                .fillMaxWidth())
        }


        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 34.dp, horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.digi_user),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.auth),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                }


                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(80.dp)
                        .alpha(0.2f), color = Color.LightGray
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.digi_club),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.club),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                }

                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(80.dp)
                        .alpha(0.2f), color = Color.LightGray
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.digi_contact_us),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.contact_us),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                }


            }
        }




        item {
            Spacer(modifier = Modifier
                .height(10.dp)
                .background(searchBarBg)
                .fillMaxWidth())
        }







        item {
            Text(text = stringResource(id = R.string.my_orders), style = MaterialTheme.typography.h5, color = Color.Black, fontWeight = FontWeight.SemiBold
            , modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 20.dp))
        }

        item {
            LazyRow() {
                item {
                    orderItems(painter = R.drawable.digi_unpaid, title = R.string.unpaid)
                }

                item {
                    orderItems(painter = R.drawable.digi_club, title = R.string.processing)
                }

                item {
                    orderItems(painter = R.drawable.digi_cancel, title = R.string.returned)
                }

                item {
                    orderItems(painter = R.drawable.digi_club, title = R.string.canceled)
                }

                item {
                    orderItems(painter = R.drawable.digi_club, title = R.string.contact_us)
                }

                item {
                    orderItems(painter = R.drawable.digi_club, title = R.string.contact_us)
                }

                item {
                    orderItems(painter = R.drawable.digi_club, title = R.string.contact_us)
                }

            }
        }

        item {
            Spacer(modifier = Modifier
                .height(40.dp)
            )
        }
        item { 
            Image(painter = painterResource(id = R.drawable.digiclub1), contentDescription = "", modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 24.dp).clip(shape = RoundedCornerShape(8.dp)).height(180.dp), contentScale = ContentScale.FillBounds)
        }

        item {
            Spacer(modifier = Modifier
                .height(40.dp)
            )
        }
        item {
            MyButton(text = stringResource(id = R.string.digikala_entry), onclick = {
                GlobalScope.launch {
                    defaultLoginState.saveLoginState("0")
                }
            })
        }

        item {
            Spacer(modifier = Modifier
                .height(100.dp)
            )
        }
    }


//   Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//       MyButton(text = stringResource(id = R.string.digikala_entry), onclick = {
//           GlobalScope.launch {
//               defaultLoginState.saveLoginState("0")
//           }
//       })
//
//   }


}


@Composable
fun orderItems(painter: Int,title:Int){
    Row() {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 20.dp)) {
            Image(
                painter = painterResource(painter),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(vertical = 4.dp)
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 4.dp)
            )

        }
        Divider(
            modifier = Modifier
                .width(2.dp)
                .padding(horizontal = 20.dp)
                .height(80.dp)
                .alpha(0.2f), color = Color.LightGray
        )

    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Login(navController: NavHostController) {
    val contex = LocalContext.current
    val inputvalue = remember { mutableStateOf("09121234567") }
    val defaultLoginState = StoreLoginState(LocalContext.current)
    val default_Login = defaultLoginState.getLoginState.collectAsState(initial = "")
    val defaultTokenState = StoreTokenInfo(contex)
    var default_token = defaultTokenState.getToken.collectAsState("")

    var loginstatuss by remember {
        mutableStateOf(false)
    }

    var loginstatussloading by remember {
        mutableStateOf(false)
    }


    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current
    val loginViewmodel =
        localviewmodellownder?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }

    if (default_Login.value == "0") {
        loginstatuss = false
    }
    LaunchedEffect(key1 = true) {
        loginViewmodel?.loginstatus?.observe(lifecylleownder) { status ->
            loginstatuss = status
        }

    }

    LaunchedEffect(key1 = true) {
        loginViewmodel?.loading?.observe(lifecylleownder) { loading ->
            loginstatussloading = loading
        }

    }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    Icons.Filled.Close, contentDescription = "", modifier = Modifier
                        .padding(
                            vertical = 11.dp, horizontal = 11.dp
                        )
                        .size(24.dp), tint = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.digi_settings),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            vertical = 11.dp, horizontal = 11.dp
                        )
                        .size(24.dp),
                    tint = Color.Black
                )

            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
                modifier = Modifier.size(220.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

        item {
            Text(
                text = stringResource(id = R.string.loginTxt),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            MyEditText(
                text = inputvalue.value,
                onvalueCange = { inputvalue.value = it },
                placeHolder = stringResource(id = R.string.phone_and_email)
            )
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            if (loginstatussloading) {
                MyLoadingButton(text = stringResource(id = R.string.digikala_entry), onclick = {
                })

            } else {
                MyButton(text = stringResource(id = R.string.digikala_entry), onclick = {
                    if (loginViewmodel != null) {
                        loginViewmodel.loginAct("093536396357", "1234546", context = contex)

                    }
                })
            }

        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Divider()
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = stringResource(id = R.string.terms_and_rules_full),
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center
            )
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }

    }

}