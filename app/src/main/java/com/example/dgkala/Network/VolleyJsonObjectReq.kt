package com.example.dgkala.Network

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dgkala.Model.Slides
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import org.json.JSONObject

class VolleyJsonObjectRe(url:String){
    val gson= Gson()
    val linkTrang = url
    var responsee = MutableLiveData<List<Slides>>()
    val loading = MutableLiveData<Boolean>()


    @OptIn(DelicateCoroutinesApi::class)
    fun getSlidess(context: Context){

        val queue = Volley.newRequestQueue(context)
        val stringRequest = object: JsonObjectRequest(
            Request.Method.GET, linkTrang,null,
            Response.Listener<JSONObject> { response ->
                responsee.value=gson.fromJson(response.getString("data"), Array<Slides>::class.java).asList()
                Log.e("vole",responsee.value.toString())

            },
            Response.ErrorListener { error-> Log.d("3636", "Error is: ${error.toString()}" ) })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-api-key"] = "EB43556E671B925B9C98E74643BCA"
                return headers
            }
        }

        queue.add(stringRequest)
    }
}