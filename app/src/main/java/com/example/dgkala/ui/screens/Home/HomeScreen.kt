package com.example.dgkala.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dgkala.DataStore.StoreDefaultDir
import com.example.dgkala.DataStore.StoreDefaultLang
import com.example.dgkala.MainActivity
import com.example.dgkala.Model.*
import com.example.dgkala.Network.VolleyJsonObjectRe
import com.example.dgkala.Util.Constants.ENGLISH_LANG
import com.example.dgkala.Util.Constants.PERSIAN_LANG
import com.example.dgkala.Util.LocalUtils
import com.example.dgkala.ViewModel.*
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.Components.Loading3dots
import com.example.dgkala.ui.Components.Loading3dotsred
import com.example.dgkala.ui.Components.tipSlidesview
import com.example.dgkala.ui.screens.Home.*
import com.example.dgkala.ui.screens.Home.Supermarket.AmazingSuperMarketItemsOfferSection
import com.example.dgkala.ui.theme.darkBack
import com.example.dgkala.ui.theme.irsans
import com.example.dgkala.ui.theme.irsansbold
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import org.json.JSONObject
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun homeScreen(navController:NavHostController){

    LocalUtils.setLocate(LocalContext.current, Locale.getDefault().getLanguage())

    val context = LocalContext.current
    val cope= rememberCoroutineScope()

    val localviewmodellownder=LocalViewModelStoreOwner.current
    val lifecylleownder= LocalLifecycleOwner.current
    var slideList by remember {
        mutableStateOf(emptyList<Slides>())
    }

    var loadingslides by remember {
        mutableStateOf(false)
    }
    var errorsslides by remember {
        mutableStateOf("")
    }



    var AmazingItems by remember {
        mutableStateOf(emptyList<AmazingItems>())
    }

    var loadingAmazingItems by remember {
        mutableStateOf(false)
    }
    var errorsAmazingItems by remember {
        mutableStateOf("")
    }



    var AmazingSuperMarketItems by remember {
        mutableStateOf(emptyList<AmazingSuperMarketItems>())
    }

    var loadingAmazingSuperMarketItems by remember {
        mutableStateOf(false)
    }
    var errorsAmazingSuperMarketItems by remember {
        mutableStateOf("")
    }




    var AmazingBannerItems by remember {
        mutableStateOf(emptyList<Banners>())
    }

    var loadingBannerItems by remember {
        mutableStateOf(false)
    }
    var errorsBannerItems by remember {
        mutableStateOf("")
    }





    var HomeCAtegoriesItems by remember {
        mutableStateOf(emptyList<HomeCategories>())
    }

    var loadingCateItems by remember {
        mutableStateOf(false)
    }
    var errorsCateItems by remember {
        mutableStateOf("")
    }





    var HomeCenterBannerItems by remember {
        mutableStateOf(emptyList<HomeCenterBanners>())
    }

    var loadingCenterBannerItems by remember {
        mutableStateOf(false)
    }
    var errorsCenterBannerItems by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true){




        val viewmodel = localviewmodellownder?.let { ViewModelProvider(it).get(HomeSliderViewModel::class.java) }
        viewmodel?.getAllSliders()
        viewmodel?.slides?.observe(lifecylleownder){slides->
            slideList=slides

        }
        viewmodel?.loading?.observe(lifecylleownder){loadingg->
            loadingslides=loadingg
        }
        viewmodel?.error?.observe(lifecylleownder){erorr->
            errorsslides=erorr
        }







        val homeCateviewmodel = localviewmodellownder?.let { ViewModelProvider(it).get(HomeCategoriesViewModel::class.java) }
        homeCateviewmodel?.getAllCate()
        homeCateviewmodel?.items?.observe(lifecylleownder){slides->
            HomeCAtegoriesItems=slides

        }
        homeCateviewmodel?.loading?.observe(lifecylleownder){loadingg->
            loadingCateItems=loadingg
        }
        homeCateviewmodel?.error?.observe(lifecylleownder){erorr->
            errorsCateItems=erorr
        }





        val homeCenterBannerViewModel = localviewmodellownder?.let { ViewModelProvider(it).get(HomeCenterBannerViewModel::class.java) }
        homeCenterBannerViewModel?.getAllCenterBanners()
        homeCenterBannerViewModel?.item?.observe(lifecylleownder){slides->
            HomeCenterBannerItems=slides

        }
        homeCenterBannerViewModel?.loading?.observe(lifecylleownder){loadingg->
            loadingCenterBannerItems=loadingg
        }
        homeCenterBannerViewModel?.error?.observe(lifecylleownder){erorr->
            errorsCenterBannerItems=erorr
        }







        val viewmodelAmazingItems = localviewmodellownder?.let { ViewModelProvider(it).get(HomeAmazingItemsViewModel::class.java) }
        viewmodelAmazingItems?.getAllAmazingItems()
        viewmodelAmazingItems?.items?.observe(lifecylleownder){slides->
            AmazingItems=slides
            Log.e("3636",AmazingItems.toString())

        }
        viewmodelAmazingItems?.loading?.observe(lifecylleownder){loadingg->
            loadingAmazingItems=loadingg
        }
        viewmodelAmazingItems?.error?.observe(lifecylleownder){erorr->
            errorsAmazingItems=erorr
        }





        val viewmodelBannerItems = localviewmodellownder?.let { ViewModelProvider(it).get(HomeBannerViewModel::class.java) }
        viewmodelBannerItems?.getAllBanners()
        viewmodelBannerItems?.slides?.observe(lifecylleownder){slides->
            AmazingBannerItems=slides
            Log.e("3636",AmazingItems.toString())

        }
        viewmodelBannerItems?.loading?.observe(lifecylleownder){loadingg->
            loadingBannerItems=loadingg
        }
        viewmodelAmazingItems?.error?.observe(lifecylleownder){erorr->
            errorsBannerItems=erorr
        }




        val viewmodelAmazingSuperMarketItems = localviewmodellownder?.let { ViewModelProvider(it).get(HomeSuperMarketAmazingItemsViewModel::class.java) }
        viewmodelAmazingSuperMarketItems?.getAllSuperMarketAmazingProducts()
        viewmodelAmazingSuperMarketItems?.items?.observe(lifecylleownder){slidess->
            AmazingSuperMarketItems=slidess

        }
        viewmodelAmazingSuperMarketItems?.loading?.observe(lifecylleownder){loadingg->
            loadingAmazingSuperMarketItems=loadingg
        }
        viewmodelAmazingSuperMarketItems?.error?.observe(lifecylleownder){erorr->
            errorsAmazingSuperMarketItems=erorr
        }




    }











    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        navController.navigate(Screen.home.route){popUpTo(Screen.home.route){inclusive=true} }
        refreshing = false
    }
    val state = rememberPullRefreshState(refreshing, ::refresh)



    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(state)
        ) {

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
        ) {
            if (!refreshing) {
                if (errorsslides!="" && errorsAmazingItems!="" && errorsAmazingSuperMarketItems!="" && errorsBannerItems!="" && errorsCateItems!="" && errorsCenterBannerItems!=""){
                    item {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "مشکل اینترنت", fontFamily = irsansbold, modifier = Modifier.padding(10.dp))
                            Button(onClick = {
                                navController.navigate(Screen.home.route)
                                {
                                    popUpTo(Screen.home.route){
                                        inclusive=true
                                    }
                                }

                            }, modifier = Modifier
                                .padding(10.dp)
                                .width(120.dp)
                                .height(40.dp), shape = RoundedCornerShape(10.dp)) {
                                Text(text = "تلاش مجدد" , fontFamily = irsans, color = Color.White, fontSize = 10.sp)
                            }


                        }
                    }
                }else{
                    if (loadingslides){
                        item {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 240.dp), contentAlignment = Center){
                                Loading3dotsred()
                            }

                        }
                    }else{
                        item {
                            SearchBarSection()
                            tipSlidesview(slideList = slideList, context = context)
                            CategorySection(navController = navController)
                        }

                        if (HomeCenterBannerItems.size>0){
                            item {
                                CenterBanerSection(navController = navController, item = HomeCenterBannerItems[0])
                            }
                        }


                        if (!loadingAmazingItems){

                            item {
                                AmazingItemsOfferSection(navController = navController, amazingIntems = AmazingItems)
                            }

                            item {
                                CenterBanerSection(navController = navController, item = HomeCenterBannerItems[1])
                            }

                        }
                        if (!loadingBannerItems){
                            item {
                                BannerSection(navController = navController, banners = AmazingBannerItems)

                            }
                        }

                        if (!loadingAmazingSuperMarketItems){
                            item {
                                AmazingSuperMarketItemsOfferSection(
                                    navController = navController,
                                    amazingIntems = AmazingSuperMarketItems
                                )

                            }
                        }
                        if (HomeCenterBannerItems.size>0){
                            item {
                                CenterBanerSection(navController = navController, item = HomeCenterBannerItems[2])
                            }
                        }

                        if (!loadingCateItems){
                            item {
                                HomeCateSection(controller = navController, cateItems = HomeCAtegoriesItems)
                                if (HomeCenterBannerItems.size>0){
                                    CenterBanerSection(navController = navController, item = HomeCenterBannerItems[3])

                                }
                                CategorySection(navController = navController)

                            }
                        }
                        if (HomeCenterBannerItems.size>0){
                            item {
                                CenterBanerSection(navController = navController, item = HomeCenterBannerItems[4])
                            }
                        }

                    }

                }


            }


        }

        //standard Pull-Refresh indicator. You can also use a custom indicator
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PullRefreshIndicator(refreshing, state)

        }

    }

    //pullRefresh modifier



}




@OptIn(DelicateCoroutinesApi::class)
@Composable
fun mainLight(context: Context,defaultLangDataStore:StoreDefaultLang,defaultDir:StoreDefaultDir){
    val activity= context as Activity

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            activity.apply {
                GlobalScope.launch {
                    defaultLangDataStore.saveDefaultLang(PERSIAN_LANG)
                    defaultDir.saveDefaultDir("Rtl")
                }
                finish()
                startActivity(Intent(activity,MainActivity::class.java))
            }

        }, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Text(text = "Fa")
        }

        Button(onClick = {

            activity.apply {
                GlobalScope.launch {
                    defaultLangDataStore.saveDefaultLang(ENGLISH_LANG)
                    defaultDir.saveDefaultDir("Ltr")

                }
                finish()
                startActivity(Intent(activity,MainActivity::class.java))
            }


        }, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Text(text = "En")
        }
        
    }

}





