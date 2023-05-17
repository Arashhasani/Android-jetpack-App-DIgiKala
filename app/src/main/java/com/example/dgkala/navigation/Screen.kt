package com.example.dgkala.navigation

sealed class Screen (val route:String){
    object splash:Screen("splash_screen")
    object home:Screen("home_screen")
    object category:Screen("category_screen")
    object basket:Screen("basket_screen")
    object profile:Screen("profile_screen")
    object webView:Screen("web_view")
    fun withArgs(vararg args:Any):String{

        return buildString{
            append(route)
            args.forEach {
                append("/${it}")
            }
        }

    }
}