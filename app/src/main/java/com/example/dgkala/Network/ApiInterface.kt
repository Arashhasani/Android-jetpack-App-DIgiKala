package com.example.dgkala.Network

import com.example.dgkala.Model.LoginInfo
import com.example.dgkala.Model.Slides
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface{

//    @Headers("x-api-key:EB43556E671B925B9C98E74643BCA")
    @GET("getSlider")
    suspend fun getAllSlides():Response<JsonObject>

    @GET("getAmazingProducts")
    suspend fun getAmazingProducts():Response<JsonObject>


    @GET("getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingProducts():Response<JsonObject>




    @GET("get4Banners")
    suspend fun get4Banners():Response<JsonObject>

    @GET("getCategories")
    suspend fun getCategories():Response<JsonObject>


    @GET("getCenterBanners")
    suspend fun getCenterBanners():Response<JsonObject>



    @GET("getSubCategories")
    suspend fun getSubCategories():Response<JsonObject>


    @GET("getAllProducts")
    suspend fun getBasketAllProducts():Response<JsonObject>

    @POST("login")
    suspend fun login(@Body logininfor: LoginInfo?):Response<JsonObject>

}