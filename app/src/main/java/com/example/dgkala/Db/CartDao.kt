package com.example.dgkala.Db

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.dgkala.Model.Card.CArtTypes
import com.example.dgkala.Model.Card.CardItems
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {
    @Query("SELECT * FROM cart where cartstatus = 'CURRENT'")
    fun getAllCurrent(): Flow<List<CardItems>>

    @Query("SELECT * FROM cart where cartstatus = 'NEXT'")
    fun getAllNextItems(): Flow<List<CardItems>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item:CardItems)

    @Query("DELETE FROM cart")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFromCart(item:CardItems)

    @Query("update cart set count=:newCount where id=:id")
    suspend fun updateCartItem(id:String,newCount:Int)

    @Query("update cart set cartstatus = 'NEXT' where id=:id")
    suspend fun moveToNext(id:String)


    @Query("update cart set cartstatus = 'CURRENT' where id=:id")
    suspend fun moveToCurrent(id:String)



    @Query("select SUM(count) as count from cart where cartstatus='CURRENT'")
     fun CURRENTtotalitemCartCount():Flow<Int>

    @Query("select total(count) as count from cart where cartstatus='NEXT'")
     fun NEXTtotalitemCartCount():Flow<Int>
}