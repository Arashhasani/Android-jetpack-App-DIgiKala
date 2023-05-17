package com.example.dgkala.ui.Components

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dgkala.Model.Slides
import com.example.dgkala.ViewModel.HomeSliderViewModel
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.theme.irsans
import com.example.dgkala.ui.theme.irsansbold
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.gson.Gson
import kotlinx.coroutines.delay
import org.json.JSONObject

@Composable
fun TopSliderr(context: Context,navController:NavController) {
    val ac = LocalContext.current as Activity

    val localviewmodellownder=LocalViewModelStoreOwner.current
    val lifecylleownder= LocalLifecycleOwner.current
    var slideList by remember {
        mutableStateOf(emptyList<Slides>())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    var errors by remember {
        mutableStateOf("")
    }

   LaunchedEffect(key1 = true){
       val viewmodel = localviewmodellownder?.let { ViewModelProvider(it).get(HomeSliderViewModel::class.java) }
       viewmodel?.getAllSliders()
       viewmodel?.slides?.observe(lifecylleownder){slides->
           slideList=slides

       }
       viewmodel?.loading?.observe(lifecylleownder){loadingg->
           loading=loadingg
       }
       viewmodel?.error?.observe(lifecylleownder){erorr->
           errors=erorr
       }
   }


    if (errors!=""){
        Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "مشکل اینترنت", fontFamily = irsansbold, modifier = Modifier.padding(10.dp))
            Button(onClick = {
                navController.navigate(Screen.home.route)
                {
                    popUpTo(Screen.home.route){
                        inclusive=true
                    }
                }

                             }, modifier = Modifier.padding(10.dp).width(120.dp).height(40.dp), shape = RoundedCornerShape(10.dp)) {
                Text(text = "تلاش مجدد" , fontFamily = irsans, color = Color.White, fontSize = 10.sp)
            }


        }
    }else{
        if (loading){
            Box(modifier = Modifier.fillMaxSize().padding(top = 100.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }else{
            tipSlidesview(slideList = slideList, context = context)
        }
    }
   

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tipSlidesview(slideList:List<Slides>,context: Context){


    Column(modifier = Modifier
        .background(Color.White)
        .height(200.dp)
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 8.dp, vertical = 8.dp)) {
            val pagerState = rememberPagerState();
            var imageUrl by remember {
                mutableStateOf("")
            }
            HorizontalPager(slideList.size, state = pagerState, contentPadding = PaddingValues(horizontal = 10.dp), modifier = Modifier
                .weight(1f)
                .fillMaxWidth()) {page->
                imageUrl=slideList[page].image
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    val painter= rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(imageUrl).apply (block = fun ImageRequest.Builder.(){scale(Scale.FILL)}).build()
                    )
                    Image(painter = painter, contentDescription = "", modifier = Modifier
                        .padding(8.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.FillBounds)
                    HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier
                        .align(
                            Alignment.BottomEnd
                        )
                        .padding(20.dp), indicatorShape = CircleShape, inactiveColor = Color.LightGray, indicatorHeight = 8.dp, indicatorWidth = 8.dp, activeColor = Color.Black , pageCount = slideList.size)


                    
                }


            }
            LaunchedEffect(key1 = pagerState.currentPage ){
                delay(4000)
                var newPosition=pagerState.currentPage+1
                if (newPosition>slideList.size-1){
                    newPosition=0
                }
                pagerState.scrollToPage(newPosition)
            }

        }

    }
    val imageLoader = ImageLoader(context)

//    LazyRow(){
//        items(slideList.size){
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .height(140.dp)
//                .padding(10.dp), shape = RoundedCornerShape(10.dp)
//            ) {
//                AsyncImage(
//                    model = slideList[it].image,
//                    contentDescription = null
//                )
//
//            }
//        }
//    }
}

