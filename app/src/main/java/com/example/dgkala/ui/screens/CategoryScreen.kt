package com.example.dgkala.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.example.dgkala.Model.Category.SubCategory
import com.example.dgkala.Model.HomeCenterBanners
import com.example.dgkala.Util.LocalUtils
import com.example.dgkala.ViewModel.CategoryViewModel
import com.example.dgkala.ViewModel.HomeSliderViewModel
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.Components.Loading3dotsred
import com.example.dgkala.ui.screens.Home.CategorySection
import com.example.dgkala.ui.screens.Home.SearchBarSection
import com.example.dgkala.ui.theme.irsans
import com.example.dgkala.ui.theme.irsansbold
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun categoryScreen(navController: NavHostController) {
    LocalUtils.setLocate(LocalContext.current, Locale.getDefault().getLanguage())

    val context = LocalContext.current
    val cope = rememberCoroutineScope()

    val localviewmodellownder = LocalViewModelStoreOwner.current
    val lifecylleownder = LocalLifecycleOwner.current

    var tool by remember {
        mutableStateOf(emptyList<SubCategory>())
    }


    var digital by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var mobile by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var supermarket by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var fashion by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var native by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var toy by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var beauty by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var home by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var book by remember {
        mutableStateOf(emptyList<SubCategory>())
    }
    var sport by remember {
        mutableStateOf(emptyList<SubCategory>())
    }


    var categoryloading by remember {
        mutableStateOf(false)
    }
    var categoryerror by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {


        val categoryviewmodel =
            localviewmodellownder?.let { ViewModelProvider(it).get(CategoryViewModel::class.java) }
        categoryviewmodel?.getAllSubCates()
        categoryviewmodel?.tool?.observe(lifecylleownder) { slides ->
            tool = slides

        }



        categoryviewmodel?.digital?.observe(lifecylleownder) { slides ->
            digital = slides

        }

        categoryviewmodel?.mobile?.observe(lifecylleownder) { slides ->
            mobile = slides

        }
        categoryviewmodel?.supermarket?.observe(lifecylleownder) { slides ->
            supermarket = slides

        }
        categoryviewmodel?.fashion?.observe(lifecylleownder) { slides ->
            fashion = slides

        }
        categoryviewmodel?.native?.observe(lifecylleownder) { slides ->
            native = slides

        }
        categoryviewmodel?.toy?.observe(lifecylleownder) { slides ->
            toy = slides

        }
        categoryviewmodel?.beauty?.observe(lifecylleownder) { slides ->
            beauty = slides

        }
        categoryviewmodel?.home?.observe(lifecylleownder) { slides ->
            home = slides

        }
        categoryviewmodel?.book?.observe(lifecylleownder) { slides ->
            book = slides

        }

        categoryviewmodel?.sport?.observe(lifecylleownder) { slides ->
            sport = slides

        }
        categoryviewmodel?.loading?.observe(lifecylleownder) { loadingg ->
            categoryloading = loadingg
        }
        categoryviewmodel?.error?.observe(lifecylleownder) { erorr ->
            categoryerror = erorr
        }


    }


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



    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            if (!refreshing) {
                if (categoryerror != "") {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
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
                } else {



                    if (!categoryloading){
                        item {
                            SearchBarSection()
                            com.example.dgkala.ui.screens.Category.CategorySection(
                                navController = navController,
                                title = "مد و پوشاک",
                                itemlist = tool
                            )

                            com.example.dgkala.ui.screens.Category.CategorySection(
                                navController = navController,
                                title = "کالای دیجیتال",
                                itemlist = digital
                            )

                            com.example.dgkala.ui.screens.Category.CategorySection(
                                navController = navController,
                                title = "موبایل",
                                itemlist = mobile
                            )


                            com.example.dgkala.ui.screens.Category.CategorySection(
                                navController = navController,
                                title = "زیبایی",
                                itemlist = beauty
                            )

                            com.example.dgkala.ui.screens.Category.CategorySection(
                                navController = navController,
                                title = "سوپرمارکت",
                                itemlist = supermarket
                            )
                        }
                    }else{
                        item {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 240.dp), contentAlignment = Alignment.Center
                            ){
                                Loading3dotsred()
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

}