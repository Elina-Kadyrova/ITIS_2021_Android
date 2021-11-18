package com.itis.firstapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.itis.firstapp.R
import com.itis.firstapp.databinding.RabbitAddingBinding
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository

class AddDialogFragment: DialogFragment() {
    private var binding: RabbitAddingBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Добавьте кролика")
                .setView(LayoutInflater.from(context).inflate(R.layout.rabbit_adding,null))
                .setMessage("Напишите имя, породу и позицию в списке")
                .setCancelable(true)
                .setNegativeButton("Отмена") {
                        dialog, id ->
                    dialog.cancel()
                }
                .setPositiveButton("ОК",
                    DialogInterface.OnClickListener {
                            dialog, id ->
                        addInputRabbit()
                    }
                )
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun addInputRabbit(){
        with(binding){
            val name = this?.etName?.text.toString()
            if(name != ""){
                val rabbit = Rabbit(
                    RabbitRepository.rabbitsList.size,
                    this?.etName?.text.toString(),
                    this?.etBreed?.text.toString())
                RabbitRepository.addRabbit(this?.etPosition?.text.toString(), rabbit)
            }
        }
    }
}
