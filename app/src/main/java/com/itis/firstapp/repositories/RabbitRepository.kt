package com.itis.firstapp.repositories

import com.itis.firstapp.models.Rabbit

object RabbitRepository {

    val rabbitsList = arrayListOf(
        Rabbit(1, "Пушистик", "Ангорский кролик"),
        Rabbit(2, "Малыш", "Фландр"),
        Rabbit(3, "Линди", "Кролик-бабочка"),
        Rabbit(4, "Оливер", "Рекс"),
        Rabbit(5, "Снежинка", "Гермелин"),
        Rabbit(6, "Джеф", "Бургундский кролик"),
        Rabbit(7,"Джо", "Калифорнийский кролик"),
        Rabbit(8, "Гном", "Карликовая бабочка")
    )

    fun addRabbit(rabIndex:String?, rabbit: Rabbit){

    }

}
