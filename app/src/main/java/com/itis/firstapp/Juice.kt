package com.itis.firstapp

class Juice(
    name: String,
    price: Double,
    expirationDate: Int, //in days
    var volume: Int //in milliliters
):FoodProduct(name, price, expirationDate), Drinkable {
    override fun beDrunk() {
        println("I'm juice and I was drunk")
    }

    override fun beSpilled() {
        println("I'm juice and I was spilled")
    }

    override fun bePoured() {
        println("I'm juice and I was poured")
    }
}
