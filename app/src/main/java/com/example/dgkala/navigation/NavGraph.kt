package com.example.dgkala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dgkala.ui.screens.*
import com.example.dgkala.ui.screens.Home.WebPageScreen


@Composable
fun setUpNavGraph(navController:NavHostController){
    NavHost(navController = navController,
    startDestination =Screen.splash.route
    ){

        composable(route=Screen.splash.route){
            splashScree(navController)
        }

        composable(route=Screen.home.route){
            homeScreen(navController)
        }

        composable(route=Screen.category.route){
            categoryScreen(navController)
        }

        composable(route=Screen.basket.route){
            basketScreen(navController)
        }

        composable(route=Screen.profile.route){
            profileScreen(navController)
        }

        composable(route=Screen.webView.route+"?url={url}",
        arguments = listOf(navArgument("url"){
            type= NavType.StringType
            defaultValue=""
            nullable
        })
        ){
            it.arguments?.getString("url")
                ?.let { it1 -> WebPageScreen(navController = navController, url = it1) }
        }

    }

}