package com.example.chatterbox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var  auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth= FirebaseAuth.getInstance()
        val view= inflater.inflate(R.layout.fragment_login, container, false)
        val login=view.findViewById<Button>(R.id.btnLogin)
        val email =view.findViewById<EditText>(R.id.inputEmail)
        val password = view.findViewById<EditText>(R.id.inputPassword)

        val txt =view.findViewById<TextView>(R.id.gotoRegister)

        login.setOnClickListener {
            Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
            auth.signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){

                    findNavController().navigate(R.id.action_loginFragment_to_dashBoardFragment)
                }
                else{
                    Toast.makeText(activity, "try again", Toast.LENGTH_SHORT).show()
                    Log.d("fire",it.toString())
                }
            }
        }

        txt.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
        return  view
    }


}