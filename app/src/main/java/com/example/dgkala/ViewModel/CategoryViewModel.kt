package com.example.dgkala.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dgkala.Model.Banners
import com.example.dgkala.Model.Category.SubCategory
import com.example.dgkala.Model.Slides
import com.example.dgkala.Network.ApiClient
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel:ViewModel() {

    val tool= MutableLiveData<List<SubCategory>>()
    val digital= MutableLiveData<List<SubCategory>>()
    val mobile= MutableLiveData<List<SubCategory>>()
    val supermarket= MutableLiveData<List<SubCategory>>()
    val fashion= MutableLiveData<List<SubCategory>>()
    val native= MutableLiveData<List<SubCategory>>()
    val toy= MutableLiveData<List<SubCategory>>()
    val beauty= MutableLiveData<List<SubCategory>>()
    val home= MutableLiveData<List<SubCategory>>()
    val book= MutableLiveData<List<SubCategory>>()
    val sport= MutableLiveData<List<SubCategory>>()
    val error= MutableLiveData<String>()
    val loading= MutableLiveData<Boolean>()
    fun getAllSubCates(){
        Log.e("subcat","start ...")

        loading.value=true
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                try {

                    val response=ApiClient.api.getSubCategories()
                    Log.e("subcat",response.code().toString())
                    val gson=Gson()
                    if (response.isSuccessful && response.body()!=null){
                        response.body()!!["data"].asJsonObject?.let { allSlides->
                            Log.e("subcat",allSlides["tool"].toString())
                            tool.value= gson.fromJson(allSlides["tool"], Array<SubCategory>::class.java).asList()
                            digital.value= gson.fromJson(allSlides["digital"], Array<SubCategory>::class.java).asList()
                            mobile.value= gson.fromJson(allSlides["mobile"], Array<SubCategory>::class.java).asList()
                            supermarket.value= gson.fromJson(allSlides["supermarket"], Array<SubCategory>::class.java).asList()
                            fashion.value= gson.fromJson(allSlides["fashion"], Array<SubCategory>::class.java).asList()
                            native.value= gson.fromJson(allSlides["native"], Array<SubCategory>::class.java).asList()
                            toy.value= gson.fromJson(allSlides["toy"], Array<SubCategory>::class.java).asList()
                            beauty.value= gson.fromJson(allSlides["beauty"], Array<SubCategory>::class.java).asList()
                            home.value= gson.fromJson(allSlides["home"], Array<SubCategory>::class.java).asList()
                            book.value= gson.fromJson(allSlides["book"], Array<SubCategory>::class.java).asList()
                            sport.value= gson.fromJson(allSlides["sport"], Array<SubCategory>::class.java).asList()
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