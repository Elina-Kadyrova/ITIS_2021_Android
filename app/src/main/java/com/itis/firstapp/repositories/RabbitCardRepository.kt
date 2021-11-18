package com.itis.firstapp.repositories

import com.itis.firstapp.R
import com.itis.firstapp.models.RabbitCard

object RabbitCardRepository {

    val list_1 : List<Int> = listOf(
        R.drawable.photo_2,
        R.drawable.photo_1,
        R.drawable.photo_12,
        R.drawable.photo_6,
        R.drawable.photo_11)

    val list_2 : List<Int> = listOf(
        R.drawable.photo_10,
        R.drawable.photo_4,
        R.drawable.photo_3,
        R.drawable.photo_7)

    val list_3 : List<Int> = listOf(
        R.drawable.photo_5,
        R.drawable.photo_9,
        R.drawable.photo_8)

    val cardsList : List<RabbitCard> = listOf(
        RabbitCard("funny_rabbits","Много забавных и милых кроликов", list_3),
        RabbitCard("bunny.world","Ещё больше очень миленьких кроликов", list_2),
        RabbitCard("honeybunny","Невероятно много милейших ушастых кроликов", list_1),
    )
}
