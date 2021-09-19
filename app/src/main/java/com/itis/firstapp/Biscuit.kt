package com.itis.firstapp

class Biscuit(
    name: String,
    price: Double,
    expirationDate: Int, //in days
    var weight: Int //in gramms
):FoodProduct(name, price, expirationDate), Eatable {
    override fun beEaten() {
        println("I'm biscuit and I was eaten")
    }

    override fun beBittenOff() {
        println("I'm biscuit and I was bitten off")
    }
}
