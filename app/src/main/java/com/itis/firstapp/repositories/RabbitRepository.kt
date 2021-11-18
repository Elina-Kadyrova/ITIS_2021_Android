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

    fun getRabbitById(id: Int): Rabbit {
        return this.rabbitsList[id-1]
    }

    fun addRabbit(rabbitId:String?, rabbit: Rabbit){
        if (rabbitId != null  && rabbitId != "") {
            if (rabbitId.toInt() > rabbitsList.size){
                rabbit.id = i++
                rabbitsList.add(rabbit)
            }
            else{
                rabbit.id = i++
                rabbitsList.add(rabbitId.toInt() - 1, rabbit)
            }
        }
        else{
            rabbit.id = i++
            rabbitsList.add(rabbit)
        }
    }

    fun removeRabbit(rabbit: Rabbit){
        rabbitsList.remove(rabbit)
    }

    fun rabbitList(): ArrayList<Rabbit> {
        return rabbitsList
    }
}
