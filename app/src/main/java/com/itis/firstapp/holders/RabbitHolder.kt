package com.itis.firstapp.holders

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.adapters.RabbitAdapter
import com.itis.firstapp.databinding.ItemRabbitBinding
import com.itis.firstapp.fragments.PetsFragment
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository

class RabbitHolder (
    item: View,
    private val onDeleteItemAction: (Rabbit) -> Unit
) : RecyclerView.ViewHolder(item) {

    private val KEY_NAME = "NAME"
    private val KEY_BREED = "BREED"


    private var rabbit: Rabbit? = null
    private val binding =  ItemRabbitBinding.bind(item)

    fun bind(item: Rabbit) {
        this.rabbit = item
        with(binding) {
            tvName.text = item.name
            tvBreed.text = item.breed
            itemDelete.setOnClickListener{
                onDeleteItemAction(item)
            }
        }
    }

    fun updateFields(bundle: Bundle?) {
        with(binding) {
            if (bundle?.containsKey(KEY_NAME) == true) {
                bundle.getString(KEY_NAME).also {
                    tvName.text = it
                }
            }
            if (bundle?.containsKey(KEY_BREED) == true) {
                bundle.getString(KEY_BREED).also {
                    tvBreed.text = it
                }
            }
        }
    }
}
