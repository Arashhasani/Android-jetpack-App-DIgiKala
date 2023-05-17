package com.example.dgkala.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dgkala.Model.Card.CardItems
import com.example.dgkala.Util.Constants.DATABASE_NAME

@Database(entities = [CardItems::class], version = 1, exportSchema = false)
abstract class ShopDb() : RoomDatabase(){
    abstract fun cartDao(): CartDao



    companion object{
        @Volatile
        private var INSTANCE:ShopDb?=null

        fun getDb(context: Context):ShopDb{
            if (INSTANCE!=null){
                return INSTANCE as ShopDb
            }else{
                synchronized(this){
                    return Room.databaseBuilder(context.applicationContext,ShopDb::class.java,
                        DATABASE_NAME).build()
                }
            }
        }
    }



}