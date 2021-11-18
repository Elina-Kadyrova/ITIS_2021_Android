package com.itis.firstapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.itis.firstapp.R
import com.itis.firstapp.databinding.RabbitAddingBinding
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository

class AddDialogFragment: DialogFragment() {
    private var binding: RabbitAddingBinding? = null
    var positiveCallBack: ((Array<String>) -> Unit)? = null
    var negativeCallBack: ((Boolean) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return parentFragment?.let {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Добавьте кролика")
                .setView(RabbitAddingBinding.inflate(layoutInflater).let {
                    binding = it
                    it.root
                 })
                .setMessage("Напишите имя, породу и позицию в списке")
                .setCancelable(true)
                .setNegativeButton("Отмена") {
                        dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("ОК") {
                        _, _ ->
                    addInputRabbit()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun addInputRabbit(){
        with(binding){
            val name = this?.etName?.text.toString()
            val breed = this?.etBreed?.text.toString()
            val position = this?.etPosition?.text.toString()
            if (name.isNotEmpty() && breed.isNotEmpty()){
                val array = arrayOf(name, breed, position)
                positiveCallBack?.invoke(array)
            }
            else{
                negativeCallBack?.invoke(false)
            }
        }
    }

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positive: (Array<String>) -> Unit,
            negative: ((Boolean) -> Unit)? = null
        ) {
            AddDialogFragment().apply {
                positiveCallBack = positive
                negativeCallBack = negative
                show(fragmentManager, AddDialogFragment::class.java.name)
            }
        }
    }
}
