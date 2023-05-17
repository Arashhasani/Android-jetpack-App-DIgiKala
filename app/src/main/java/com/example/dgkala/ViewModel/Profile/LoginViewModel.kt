package com.example.dgkala.ViewModel.Profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dgkala.DataStore.StoreLoginState
import com.example.dgkala.DataStore.StorePasswordInfo
import com.example.dgkala.DataStore.StoreTokenInfo
import com.example.dgkala.DataStore.StoreUserInfo
import com.example.dgkala.Model.Banners
import com.example.dgkala.Model.LoginInfo
import com.example.dgkala.Model.Slides
import com.example.dgkala.Network.ApiClient
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class LoginViewModel:ViewModel() {

    val loginstatus= MutableLiveData<Boolean>()
    val error= MutableLiveData<String>()
    val loading= MutableLiveData<Boolean>()
    @OptIn(DelicateCoroutinesApi::class)
    fun loginAct(phone:String, password:String, context: Context){
        loginstatus.value=false
        error.value=""
        val defaultUserState= StoreUserInfo(context)
        val defaultTokenState= StoreTokenInfo(context)
        val defaultPasswordStore= StorePasswordInfo(context)
        val defaultloginStore= StoreLoginState(context)
        val logininfo=LoginInfo(phone,password)
        Log.e("3636","start ...")

        loading.value=true
        CoroutineScope(Dispatchers.IO).launch {


            withContext(Dispatchers.Main){
                try {

                    val response=ApiClient.api.login(logininfo)
                    Log.e("3636",response.code().toString())
                    Log.e("3636", response.body()!!["message"].toString())
                    val gson=Gson()


                    if (response.isSuccessful && response.body()!=null){
                        if (response.body()!!["success"].toString()=="true"){

                            defaultTokenState.saveToken(response.body()!!["data"].asJsonObject["token"].toString())
                            defaultPasswordStore.savePassword(password.toString())
                            defaultUserState.saveUserInfor(phone.toString())
                            defaultloginStore.saveLoginState("1")
                            loginstatus.value=true
                            Toast.makeText(context,response.body()!!["message"].toString(),Toast.LENGTH_SHORT).show()
                            Log.e("3636", response.body()!!["success"].toString())
                            loading.value=false
                        }

//                        response.body()!!["success"]?.let { allSlides->
//                            loginstatus.value= gson.fromJson(allSlides, Array<Banners>::class.java).asList()
//                            loading.value=false
//                        }
                    }else{
                        defaultPasswordStore.savePassword("")
                        defaultUserState.saveUserInfor("")
                        defaultTokenState.saveToken("")
                        defaultloginStore.saveLoginState("0")
                        error.value=response.message()
                        Log.e("3636",response.message().toString())
                        Toast.makeText(context,"خطا ...",Toast.LENGTH_SHORT).show()
                        loading.value=false
                    }

                }catch (e:Exception) {
                    defaultPasswordStore.savePassword("")
                    defaultUserState.saveUserInfor("")
                    defaultTokenState.saveToken("")
                    defaultloginStore.saveLoginState("0")
                    Log.e("3636",e.message.toString())
                    error.value=e.message.toString()
                    Log.e("3636",error.value.toString())
                    Toast.makeText(context,"خطا ...",Toast.LENGTH_SHORT).show()
                    loading.value=false


                }


            }




        }

    }
}