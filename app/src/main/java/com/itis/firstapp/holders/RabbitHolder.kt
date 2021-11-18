package com.itis.firstapp.holders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemRabbitBinding
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository

class RabbitHolder (
    item: View,
    private val onDeleteItemAction: (Rabbit) -> Unit
) : RecyclerView.ViewHolder(item)  {

    private val KEY_NAME = "NAME"
    private val KEY_BREED = "BREED"
    private var rabbit: Rabbit? = null
    private var binding: ItemRabbitBinding? = ItemRabbitBinding.bind(item)

    fun bind(itemCur: Rabbit) {
        this.rabbit = itemCur
        with(binding) {
            rabbit?.let{
                this?.tvName?.text = rabbit?.name
                this?.tvBreed?.text = rabbit?.breed
                this?.itemDelete?.setOnClickListener{
                    onDeleteItemAction(itemCur)
                }
            }
        }
    }

    fun updateFields(bundle: Bundle?) {
        with(binding) {
            if (bundle?.containsKey(KEY_NAME) == true) {
                bundle.getString(KEY_NAME).also {
                    this?.tvName?.text = it
                }
            }
            if (bundle?.containsKey(KEY_BREED) == true) {
                bundle.getString(KEY_BREED).also {
                    this?.tvBreed?.text = it
                }
            }
        }
    }
}
