package com.example.dgkala

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dgkala.DataStore.*
import com.example.dgkala.Model.Slides
import com.example.dgkala.Util.Constants.ENGLISH_LANG
import com.example.dgkala.Util.Constants.PERSIAN_LANG
import com.example.dgkala.Util.LocalUtils
import com.example.dgkala.ViewModel.Cart.CartViewModel
import com.example.dgkala.ViewModel.HomeSliderViewModel
import com.example.dgkala.ViewModel.Profile.LoginViewModel
import com.example.dgkala.navigation.BottomNavigationBar
import com.example.dgkala.navigation.Screen
import com.example.dgkala.navigation.setUpNavGraph
import com.example.dgkala.ui.Components.ChangeStatusBarColor
import com.example.dgkala.ui.theme.DgkalaTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var navController:NavHostController
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val localviewmodellownder = LocalViewModelStoreOwner.current
            val lifecylleownder = LocalLifecycleOwner.current
            val context = LocalContext.current
            val cope= rememberCoroutineScope()
            val defaultLangDataStore=StoreDefaultLang(context)
            val defaultDir=StoreDefaultDir(context)
            val defaultLoginState=StoreLoginState(context)
            val default_Logins=defaultLoginState.getLoginState.collectAsState("")
            val default_lang=defaultLangDataStore.getLang.collectAsState("")
            val default_dir=defaultDir.getDir.collectAsState("")


            val defaultPhoneState=StoreUserInfo(context)
            val defaultPasswordState=StorePasswordInfo(context)


//
            val loginViewmodel =
                LocalViewModelStoreOwner.current?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }


            if (default_Logins.value.toString()=="1"){
                val defaultPhoneStatevalu=defaultPhoneState.getUserInfo.collectAsState("")
                val defaultpasswordStatevalue=defaultPasswordState.getPassword.collectAsState("")
                if ((defaultPhoneStatevalu.value !=  "") && (defaultpasswordStatevalue.value !=  "")) {
                    Log.e("363636366", defaultPhoneStatevalu.value.toString())
                    Log.e("36363636",defaultpasswordStatevalue.value.toString())
                    if (loginViewmodel != null) {
                        loginViewmodel.loginAct(defaultPhoneStatevalu.value.toString(), defaultpasswordStatevalue.value.toString(), context =context)
                    }
                }
            }



//            if (default_Logins.value=="0"){
//                loginstatuss=false
//            }else if(default_Logins.value=="1"){
//                val defaultPhoneStatevalu=defaultPhoneState.getUserInfo.collectAsState("")
//                val defaultpasswordStatevalue=defaultPasswordState.getPassword.collectAsState("")
//                if ((defaultPhoneStatevalu.value !=  null) && (defaultpasswordStatevalue.value !=  null)){
//
//                    if (loginViewmodel != null) {
//                        loginViewmodel.loginAct(defaultPhoneStatevalu.value.toString(), defaultpasswordStatevalue.value.toString(), context = LocalContext.current)
//                    }
//                    LaunchedEffect(key1 = true){
//                        loginViewmodel?.loginstatus?.observe(lifecylleownder){ status->
//                            loginstatuss=status
//                        }
//
//                    }
//                    if (loginstatuss){
//                        GlobalScope.launch {
//                            defaultLoginState.saveLoginState("1")
//                        }
//                    }else{
//                        GlobalScope.launch {
//                            defaultLoginState.saveLoginState("0")
//                        }
//                    }
//
//                }
//
//            }

            GlobalScope.launch {
                defaultLangDataStore.saveDefaultLang(PERSIAN_LANG)
                defaultDir.saveDefaultDir("Rtl")
            }


            DgkalaTheme {
                LocalUtils.setLocate(LocalContext.current, default_lang.value.toString())
                navController= rememberNavController()
                ChangeStatusBarColor(navController = navController)
                val dir:LayoutDirection=if (default_dir.value.toString()=="Rtl"){
                    LayoutDirection.Rtl
                }else{
                    LayoutDirection.Ltr
                }
                CompositionLocalProvider(LocalLayoutDirection provides dir) {

                    Scaffold(
                       
                        bottomBar = { BottomNavigationBar(navController, modifier = Modifier.fillMaxWidth(), onItemClick = {navController.navigate(it.route)})},
                        content ={setUpNavGraph(navController = navController)}
                    )

                }

            }
        }
    }

    override fun onBackPressed() {
        if (Screen.home.route.toString()==navController.currentDestination?.route.toString()){
//            navController.popBackStack()
            Log.e("3636","yesy")
           finish()

        }else{
            navController.navigate(Screen.home.route){
                popUpTo(Screen.home.route){
                    inclusive=true
                }
            }
        }


        Log.e("3636",Screen.home.route.toString())
        Log.e("3636",navController.currentDestination?.route.toString())
//        if (navController.currentDestination?.route==Screen.home.route){
//            navController.popBackStack()
//        }else{
//            navController.navigate(Screen.home.route){
//                popUpTo(Screen.home.route){
//                    inclusive=true
//                }
//            }
//        }


    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DgkalaTheme {
        Greeting("Android")
    }
}