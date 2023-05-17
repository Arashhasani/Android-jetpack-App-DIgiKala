package com.example.dgkala.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dgkala.Model.Banners
import com.example.dgkala.Model.HomeCenterBanners
import com.example.dgkala.Model.Slides
import com.example.dgkala.Network.ApiClient
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeCenterBannerViewModel:ViewModel() {

    val item= MutableLiveData<List<HomeCenterBanners>>()
    val error= MutableLiveData<String>()
    val loading= MutableLiveData<Boolean>()
    fun getAllCenterBanners(){
        Log.e("3636","start ...")

        loading.value=true
        CoroutineScope(Dispatchers.IO).launch {


            withContext(Dispatchers.Main){
                try {

                    val response=ApiClient.api.getCenterBanners()
                    Log.e("3636",response.code().toString())

                    val gson=Gson()
                    if (response.isSuccessful && response.body()!=null){
                        response.body()!!["data"]?.let { allSlides->
                            item.value= gson.fromJson(allSlides, Array<HomeCenterBanners>::class.java).asList()
                            loading.value=false
                        }
                    }else{
                        error.value=response.message()
                        Log.e("3636",response.message().toString())
                        loading.value=false
                    }

                }catch (e:Exception) {
                    Log.e("3636",e.message.toString())
                    error.value=e.message.toString()
                    Log.e("3636",error.value.toString())


                }


            }




        }

    }
}