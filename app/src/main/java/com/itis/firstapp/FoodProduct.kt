package com.itis.firstapp

open class FoodProduct constructor(
    val name: String,
    var price: Double = 0.0,
    val expirationDate: Int //in days
) {
}
