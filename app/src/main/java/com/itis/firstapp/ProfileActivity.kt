package com.itis.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.firstapp.databinding.RabbitProfileBinding

class ProfileActivity: AppCompatActivity() {

    private lateinit var binding: RabbitProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RabbitProfileBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        print(intent.extras?.getInt("id"))
        val id = intent.extras?.getInt("id")
        with(binding){
            val rabbit = id?.let {
                RabbitRepository.getRabbitById(id)
            }
            val name = rabbit?.name
            val breed = rabbit?.breed
            val description = rabbit?.description

            rabbit?.photo?.let {
                ivPhoto.setImageResource(it)
                tvNameProfile.text = "Имя: $name"
                tvBreedProfile.text = "Порода: $breed"
                tvDescriptionProfile.text = "Описание: $description"
            }
        }
    }
}
