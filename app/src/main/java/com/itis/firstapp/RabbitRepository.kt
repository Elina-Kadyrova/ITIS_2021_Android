package com.itis.firstapp

object RabbitRepository {

    val rabbits = arrayListOf(
        Rabbit(
            1, R.drawable.ang, "Пушистик", "Ангорский кролик",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac leo vulputate, fermentum elit id, laoreet dui. Vivamus id condimentum metus, a imperdiet ante. Vivamus tincidunt, ante quis finibus mattis, velit nulla molestie sem, eu vestibulum est nisi ut nibh. Maecenas gravida justo purus, eu posuere turpis fringilla at. Maecenas lobortis in mi ut varius. Donec bibendum eu dolor nec dapibus. Sed malesuada porta nulla, non rutrum augue aliquet at. Donec purus nisl, scelerisque nec lorem id, faucibus aliquet sapien. Proin sit amet purus eu ligula dictum malesuada at non arcu."
        ),
        Rabbit(
            2, R.drawable.flandr, "Малыш", "Фландр",
            "Quisque aliquet efficitur sodales. Mauris eu pellentesque lectus. Morbi ullamcorper euismod euismod. Cras id ultrices dolor. Duis mollis ullamcorper ligula. Suspendisse venenatis finibus pellentesque. Proin lectus lorem, blandit tristique gravida non, fermentum sit amet dolor. Mauris viverra est viverra leo tristique tristique."
        ),
        Rabbit(
            3, R.drawable.bab, "Линди", "Кролик-бабочка",
            "Sed maximus risus fringilla pellentesque dictum. Integer in libero auctor, scelerisque velit vitae, condimentum libero. Aenean vitae ligula ac nisl varius fringilla. Fusce massa lectus, mollis at enim eu, cursus iaculis erat. Sed bibendum augue quis lectus placerat, eget malesuada mi vehicula. Morbi vitae commodo magna, vitae gravida orci. Nulla dignissim tristique varius. "
        ),
        Rabbit(
            4, R.drawable.rex, "Оливер", "Рекс",
            "Mauris mollis lobortis eros vitae imperdiet. In feugiat bibendum ipsum, eu luctus purus gravida vel. Pellentesque pellentesque, nunc nec viverra suscipit, est felis bibendum enim, nec dignissim nulla diam dignissim est. In ultrices risus velit, vel aliquam ex ultricies at. Sed tellus purus, venenatis aliquet fringilla nec, ultricies vel sem. Proin vel lorem mattis, condimentum neque eu, blandit ex."
        ),
        Rabbit(
            5, R.drawable.germelin, "Снежинка", "Гермелин",
            "Suspendisse ipsum est, posuere sed sollicitudin id, suscipit sed ipsum. Phasellus sit amet suscipit ipsum. Vivamus rutrum sit amet nibh vitae fringilla. Ut dui mauris, hendrerit in lobortis a, aliquam consequat leo. "
        ),
        Rabbit(
            6, R.drawable.burg, "Джеф", "Бургундский кролик",
            " Duis non massa consequat, sagittis enim at, ullamcorper urna. Vivamus facilisis condimentum mi. Nunc orci lacus, laoreet ac lacinia eget, eleifend ornare quam. In pharetra interdum tellus, ac posuere tellus tempor non. Maecenas ornare convallis elit, lobortis bibendum erat. Quisque at augue et velit malesuada semper. Suspendisse potenti. Quisque semper sem et sapien semper, sed varius nisl hendrerit. "
        ),
        Rabbit(
            7, R.drawable.kalif, "Джо", "Калифорнийский кролик",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac leo vulputate, fermentum elit id, laoreet dui. Vivamus id condimentum metus, a imperdiet ante. Vivamus tincidunt, ante quis finibus mattis, velit nulla molestie sem, eu vestibulum est nisi ut nibh. Maecenas gravida justo purus, eu posuere turpis fringilla at. Maecenas lobortis in mi ut varius. Donec bibendum eu dolor nec dapibus. Sed malesuada porta nulla, non rutrum augue aliquet at. Donec purus nisl, scelerisque nec lorem id, faucibus aliquet sapien. Proin sit amet purus eu ligula dictum malesuada at non arcu."
        ),
        Rabbit(
            8, R.drawable.karl_bab, "Гном", "Карликовая бабочка",
            "Quisque aliquet efficitur sodales. Mauris eu pellentesque lectus. Morbi ullamcorper euismod euismod. Cras id ultrices dolor. Duis mollis ullamcorper ligula. Suspendisse venenatis finibus pellentesque. Proin lectus lorem, blandit tristique gravida non, fermentum sit amet dolor. Mauris viverra est viverra leo tristique tristique."
        ),
        Rabbit(
            9, R.drawable.lvinogol, "Лев", "Львиноголовый кролик",
            "Sed maximus risus fringilla pellentesque dictum. Integer in libero auctor, scelerisque velit vitae, condimentum libero. Aenean vitae ligula ac nisl varius fringilla. Fusce massa lectus, mollis at enim eu, cursus iaculis erat. Sed bibendum augue quis lectus placerat, eget malesuada mi vehicula. Morbi vitae commodo magna, vitae gravida orci. Nulla dignissim tristique varius. "
        ),
        Rabbit(
            10, R.drawable.novozel, "Кокос", "Новозеландский кролик",
            "Mauris mollis lobortis eros vitae imperdiet. In feugiat bibendum ipsum, eu luctus purus gravida vel. Integer in libero auctor, scelerisque velit vitae, condimentum libero. Aenean vitae ligula ac nisl varius fringilla. Fusce massa lectus, mollis at enim eu, cursus iaculis erat. Sed bibendum augue quis lectus placerat, eget malesuada mi vehicula. Morbi vitae commodo magna, vitae gravida orci. Nulla dignissim tristique varius. "
        ),
        Rabbit(
            11, R.drawable.ser_vel, "Великан", "Серый великан",
            "Sed maximus risus fringilla pellentesque dictum. Integer in libero auctor, scelerisque velit vitae, condimentum libero. Aenean vitae ligula ac nisl varius fringilla. Fusce massa lectus, mollis at enim eu, cursus iaculis erat. Sed bibendum augue quis lectus placerat, eget malesuada mi vehicula. Morbi vitae commodo magna, vitae gravida orci. Nulla dignissim tristique varius. "
        ),
        Rabbit(
            12, R.drawable.pannon, "Ушастик", "Паннон",
            "In feugiat bibendum ipsum, eu luctus purus gravida vel. Aenean vitae ligula ac nisl varius fringilla. Fusce massa lectus, mollis at enim eu, cursus iaculis erat. Sed bibendum augue quis lectus placerat, eget malesuada mi vehicula. Morbi vitae commodo magna, vitae gravida orci. Nulla dignissim tristique varius. "
        ),
    )

    fun getRabbitById(id: Int): Rabbit {
        return this.rabbits[id-1]
    }
}
