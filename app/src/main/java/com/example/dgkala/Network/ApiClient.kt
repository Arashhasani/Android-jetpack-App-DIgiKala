package com.example.dgkala.Network

import com.example.dgkala.Util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val api:ApiInterface by lazy {
        val client=OkHttpClient.Builder().connectTimeout(10,TimeUnit.SECONDS).writeTimeout(10,TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS)
            .addInterceptor{chain->val request=chain.request().newBuilder().addHeader("x-api-key","EB43556E671B925B9C98E74643BCA").addHeader("lang","fa")
            chain.proceed(request.build())}
        .build()
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(ApiInterface::class.java)
    }

}