package com.example.dgkala.Model.Basket

data class BasketProducts(
    val _id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Int,
    val seller: String
)