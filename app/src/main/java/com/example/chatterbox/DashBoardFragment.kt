package com.example.chatterbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class DashBoardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)
        val txt=view.findViewById<TextView>(R.id.txt3)
        txt.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoardFragment_to_chatFragment)
        }

        return view
    }


}