package com.example.dgkala.ViewModel.Cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dgkala.Db.CartDao
import com.example.dgkala.Db.ShopDb
import com.example.dgkala.Model.Card.CArtTypes
import com.example.dgkala.Model.Card.CardItems
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class CartViewModel(application: Application): AndroidViewModel(application) {


    val allitemsCurrent: Flow<List<CardItems>>
    var CURRENTtotalitemCartCount: Flow<Int>
    val allitemsNext: Flow<List<CardItems>>
    val cartDao:CartDao
    var loading= MutableLiveData<Boolean>()
    init {
         cartDao=ShopDb.getDb(application).cartDao()
        allitemsCurrent=cartDao.getAllCurrent()
        allitemsNext=cartDao.getAllNextItems()
        CURRENTtotalitemCartCount= cartDao.CURRENTtotalitemCartCount()
        loading.value=false
    }
    fun addPost(item:CardItems){
        viewModelScope.launch {
            cartDao.addItem(item)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            cartDao.deleteAll()
        }
    }

    fun deleteFromCart(item:CardItems){
        viewModelScope.launch {
            cartDao.deleteFromCart(item)
        }
    }


    fun updateCartItem(id:String,newCount:Int){
        viewModelScope.launch {
            cartDao.updateCartItem(id,newCount)
        }
    }
    fun moveToNext(id:String){
        viewModelScope.launch {
            cartDao.moveToNext(id)
        }
    }

    fun moveToCurrent(id:String){
        viewModelScope.launch {
            cartDao.moveToCurrent(id)
        }
    }




}