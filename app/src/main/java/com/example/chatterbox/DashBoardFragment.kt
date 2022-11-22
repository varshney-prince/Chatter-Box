package com.example.chatterbox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class DashBoardFragment : Fragment() {
    private  lateinit var db:DatabaseReference
//    private  lateinit var tool: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)

        // custom toolbar
//        tool = view.findViewById(R.id.toolbar1)
//        val act: AppCompatActivity = activity as AppCompatActivity
//        act.setSupportActionBar(tool)
//        act.supportActionBar?.title = ""
        val logOut=view.findViewById<ImageView>(R.id.logOut)

        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

           // findNavController().navigate(R.id.action_dashBoardFragment_to_loginFragment)
            activity?.finish()
        }


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(view.context)
        var arrayList = ArrayList<User>()
        val cadpter=CustomAdapter(arrayList)
        recyclerView.adapter=cadpter

        val txt=view.findViewById<TextView>(R.id.cUser)


        db=FirebaseDatabase.getInstance().reference

        db.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(postSnapshot in snapshot.children){
                    val currentValue=postSnapshot.getValue(User::class.java)
//                    if (currentValue != null) {
//                        Log.d("user name",currentValue.name!!)
//                    }
                    if (FirebaseAuth.getInstance().currentUser?.uid!=currentValue?.uid ) {
                        Log.d("chat error","inside")
                        arrayList.add(currentValue!!)
                    }
                    else if(FirebaseAuth.getInstance().currentUser?.uid==currentValue?.uid ){
                        txt.text=currentValue!!.name
                    }
                }

                cadpter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })








//        txt.setOnClickListener {
//            findNavController().navigate(R.id.action_dashBoardFragment_to_chatFragment)
//        }

        return view
    }




}