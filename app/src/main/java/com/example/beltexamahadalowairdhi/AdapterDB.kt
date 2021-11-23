package com.example.beltexamahadalowairdhi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beltexamahadalowairdhi.databinding.DbRowBinding

class AdapterDB(val FragmentDB: DbFragment ) : RecyclerView.Adapter<AdapterDB.ViewHolder>() {
    private var listDB: List<GameEntity> = listOf()

    class ViewHolder(var binding: DbRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DbRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameInfo = listDB[position]
        //val showImg = listDB[position].id
         val descip = listDB[position].shortdescrip

        holder.binding.apply {

            tvTitle.text = gameInfo.gameTitle
            tvPlatform.text = gameInfo.platform

            Log.d("Taagg","${gameInfo.thumbnail}")
            Glide.with(FragmentDB).load(gameInfo.thumbnail).into(imgV)

            imgBtn.setOnClickListener {
                FragmentDB.DialogDel(gameInfo)
            }
            imgV.setOnClickListener{
                Toast.makeText(holder.itemView.context, "$descip", Toast.LENGTH_SHORT).show()
            }
        }

        }


        override fun getItemCount(): Int = listDB.size

    fun update(list: List<GameEntity>){
        this.listDB  = list
        notifyDataSetChanged()
    }


}