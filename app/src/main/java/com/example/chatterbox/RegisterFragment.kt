package com.example.chatterbox

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db:DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          val view = inflater.inflate(R.layout.fragment_register, container, false)
          val name = view.findViewById<EditText>(R.id.inputName1)
          val email =view.findViewById<EditText>(R.id.inputEmail1)
          val password=view.findViewById<EditText>(R.id.inputPassword1)
          val bt=view.findViewById<Button>(R.id.btnRegister)

        auth= FirebaseAuth.getInstance()
        bt.setOnClickListener {
            val semail=email.text.toString()
            val spassword=password.text.toString()
            val sname=name.text.toString()

            auth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
                    addUserToDatabase(sname,semail,auth.currentUser?.uid!!)
                    findNavController().navigate(R.id.action_registerFragment_to_dashBoardFragment)
                }


            }.addOnFailureListener {
                Log.d("fire",it.localizedMessage)
            }


        }
        return view
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        db=FirebaseDatabase.getInstance().reference
        db.child("user").child(uid).setValue(User(name,email,uid))
    }

}