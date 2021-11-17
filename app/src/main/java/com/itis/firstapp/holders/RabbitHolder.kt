package com.itis.firstapp.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemRabbitBinding
import com.itis.firstapp.models.Rabbit

class RabbitHolder (
    item: View
) : RecyclerView.ViewHolder(item) {

    private var rabbit: Rabbit? = null
    private val binding =  ItemRabbitBinding.bind(item)

    fun bind(item: Rabbit) {
        this.rabbit = item
        with(binding) {
            tvName.text = item.name
            tvBreed.text = item.breed
            itemDelete.setOnClickListener{
                
            }
        }
    }
}
