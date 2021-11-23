package com.example.beltexamahadalowairdhi

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide


class ViewFragment : Fragment() {

    val GameModel by lazy { ViewModelProvider(this).get( myViewModel ::class.java) }

    lateinit var tvShow: TextView
    lateinit var showImg : LiveData<List<GameEntity>>
    lateinit var image : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view, container, false)


        tvShow= view.findViewById(R.id.tvShow)
        image=view.findViewById(R.id.image)
        showImg= GameModel.getListgame()



        val args = this.arguments
        val ImgId = args?.get("ImgId")

//        for (i in showImg){
//            if (i.id == ImgId){
//                Glide.with(this).load(i.thumbnail).into(image)
//                tvShow.text = "${i.gameTitle}\n ${i.platform}\n ${i.shortdescrip}"
//            }



        return view
    }


}