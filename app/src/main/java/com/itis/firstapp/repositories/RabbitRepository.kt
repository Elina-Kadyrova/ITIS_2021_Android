package com.itis.firstapp.repositories

import com.itis.firstapp.models.Rabbit

object RabbitRepository {
    private var i = 1

    val rabbitsList = arrayListOf(
        Rabbit(i++, "Пушистик", "Ангорский кролик"),
        Rabbit(i++, "Малыш", "Фландр"),
        Rabbit(i++, "Линди", "Кролик-бабочка"),
        Rabbit(i++, "Оливер", "Рекс"),
        Rabbit(i++, "Снежинка", "Гермелин"),
        Rabbit(i++, "Джеф", "Бургундский кролик"),
        Rabbit(i++,"Джо", "Калифорнийский кролик"),
        Rabbit(i++, "Гном", "Карликовая бабочка")
    )

    fun addRabbit(rabbitIndex:Int, rabbit: Rabbit){
            if (rabbitIndex <= 0 || rabbitIndex > rabbitsList.size){
                rabbit.id = i++
                rabbitsList.add(rabbit)
            }
            else{
                rabbit.id = i++
                rabbitsList.add(rabbitIndex - 1, rabbit)
            }
    }

    fun removeRabbit(rabbit: Rabbit){
        rabbitsList.remove(rabbit)
    }
}
