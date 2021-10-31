package com.itis.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemRabbitBinding

class RabbitHolder (item: View) : RecyclerView.ViewHolder(item) {

    private var rabbit: Rabbit? = null

    val binding = ItemRabbitBinding.bind(item)

    fun bind(item: Rabbit) {
        this.rabbit = item
        with(binding) {
            tvName.text = item.name
            tvBreed.text = item.breed
            ivImage.setImageResource(item.photo)
        }
    }

    companion object {
        fun create(parent: ViewGroup) = RabbitHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_rabbit, parent, false))
    }
}
