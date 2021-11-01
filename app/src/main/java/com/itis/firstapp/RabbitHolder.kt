package com.itis.firstapp

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemRabbitBinding

class RabbitHolder (
    item: View
) : RecyclerView.ViewHolder(item) {

    private var rabbit: Rabbit? = null
    val binding = ItemRabbitBinding.bind(item)

    fun bind(item: Rabbit) {
        this.rabbit = item
        with(binding) {
            tvName.text = item.name
            tvBreed.text = item.breed
            ivImage.setImageResource(item.photo)
        }

        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context,ProfileActivity::class.java)
            intent.putExtra("id", item.id)
            print(item.id)
            context.startActivity(intent)
        }
    }
}
