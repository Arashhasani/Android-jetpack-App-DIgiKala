package com.example.dgkala.ui.Components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dgkala.navigation.Screen
import com.example.dgkala.ui.theme.Purple200
import com.example.dgkala.ui.theme.selectedBottomBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ChangeStatusBarColor (navController:NavHostController){

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val systemyUiController = rememberSystemUiController()
    if (navBackStackEntry?.destination?.route==Screen.splash.route){
        SideEffect {
            systemyUiController.setStatusBarColor(Purple200)
        }
    }else{
        SideEffect {
            systemyUiController.setStatusBarColor(Color.White)
        }
    }

}