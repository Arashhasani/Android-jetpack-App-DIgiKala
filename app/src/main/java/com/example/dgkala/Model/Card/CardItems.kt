package com.example.dgkala.Model.Card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CardItems(

    @PrimaryKey
    val id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Int,
    val seller: String,
    val count: Int,
    val cartstatus: CArtTypes
)
