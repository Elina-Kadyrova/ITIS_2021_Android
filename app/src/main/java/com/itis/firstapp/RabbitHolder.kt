package com.itis.firstapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemRabbitBinding

class RabbitHolder (
    private val binding: ItemRabbitBinding,
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var rabbit: Rabbit? = null

    fun bind(item: Rabbit) {
        this.rabbit = item
        with(binding) {
            tvName.text = item.name
            tvBreed.text = item.breed

            tvName.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    android.R.color.holo_red_dark
                )
            )
            ivImage.setImageResource(item.photo)
        }

        itemView.setOnClickListener {
            action(item.name)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action: (String) -> Unit
        ) = RabbitHolder(
            ItemRabbitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action)
    }
}
