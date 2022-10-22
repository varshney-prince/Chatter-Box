package com.example.chatterbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val txt=view.findViewById<TextView>(R.id.txt2)
        txt.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_dashBoardFragment)
        }
        return view
    }

}