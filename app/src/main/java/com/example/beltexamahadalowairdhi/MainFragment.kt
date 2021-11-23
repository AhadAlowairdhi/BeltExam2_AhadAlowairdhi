package com.example.beltexamahadalowairdhi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment() {

    lateinit var apiBtn : Button
    lateinit var dbBt : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // init & move to browser fragment
        apiBtn= view.findViewById(R.id.apiBt)
        apiBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_browserFragment)
        }

        // init & move to local DB fragment
        dbBt= view.findViewById(R.id.dbBt)
        dbBt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dbFragment)
        }

        return view
    }


}