package com.example.beltexamahadalowairdhi

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.beltexamahadalowairdhi.databinding.ApiRowBinding

class AdapterApi(val FragmentApi: BrowserFragment, var listGame: ArrayList<Game.gamesItem>) : RecyclerView.Adapter<AdapterApi.ViewHolder>() {
    class ViewHolder( var binding: ApiRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ApiRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val game = listGame[position]

        holder.binding.apply {
            tvGame.text = game.title
            tvGame.setOnClickListener{
                val alertDialog = AlertDialog.Builder(holder.itemView.context)

                alertDialog.setTitle("Add to DB")
                alertDialog.setPositiveButton("Add", DialogInterface.OnClickListener{
                        dialogInterface, i ->
                    // add to Local DB
                    FragmentApi.saveToLocal(game)
                })
                alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, which -> dialog.cancel()

                })
                val alert : AlertDialog = alertDialog.create()
                alert.setCanceledOnTouchOutside(false)
                alert.show()

            }
        }

    }

    override fun getItemCount(): Int = listGame.size
}