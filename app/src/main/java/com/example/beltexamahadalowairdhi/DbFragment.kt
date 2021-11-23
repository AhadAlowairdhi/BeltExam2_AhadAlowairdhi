package com.example.beltexamahadalowairdhi

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class DbFragment : Fragment() {

    private val GameModel by lazy { ViewModelProvider(this).get( myViewModel ::class.java) }
    lateinit var rvDB : RecyclerView
    lateinit var adapterDB: AdapterDB
    lateinit var backBt : Button
    lateinit var tvDisplay : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_db, container, false)

        // init RV
        rvDB = view.findViewById(R.id.rvDB)
        tvDisplay = view.findViewById(R.id.tvNothing)

        GameModel.getListgame().observe(viewLifecycleOwner,
            // access to function in adapter
            { list -> adapterDB.update(list)

                rvDB.scrollToPosition(list.size-1)

                if(list.isEmpty())
                    tvDisplay.isVisible=true

            })

        // init RV
        adapterDB = AdapterDB(this)
        rvDB.adapter = adapterDB
        rvDB.layoutManager = LinearLayoutManager(requireContext())

        //init & back main Fragment
        backBt= view.findViewById(R.id.BtHome)
        backBt.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dbFragment_to_mainFragment)
        }

        return view
    }

    fun delete(game: GameEntity){
        GameModel.delgame(game)
    }

    fun updatInfo(id : Int){
        val bundle = Bundle()
        bundle.putInt("ImgId",id)
        val fragment = ViewFragment()
        fragment.arguments=bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView,fragment)?.commit()
    }



    fun DialogDel(game : GameEntity){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Are you sure?")
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id-> GameModel.delgame(game)
                Toast.makeText(requireContext(), "Game Deleted", Toast.LENGTH_SHORT).show()

            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete Game")
        alert.show()
    }

}