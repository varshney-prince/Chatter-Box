package com.example.chatterbox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox:EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList:ArrayList<Message>
    private lateinit var mDbref:DatabaseReference

    var receiverRoom:String?=null
    var senderRoom:String?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_chat, container, false)
        chatRecyclerView=view.findViewById(R.id.chatRecyclerView)
        messageBox=view.findViewById(R.id.messageBox)
        sendButton=view.findViewById(R.id.send_btn)
        messageList= ArrayList()
        messageAdapter= MessageAdapter(view.context,messageList)
        mDbref=FirebaseDatabase.getInstance().getReference()
        chatRecyclerView.layoutManager=LinearLayoutManager(view.context)
        chatRecyclerView.adapter=messageAdapter



        val receiverId=arguments?.getString("pid")
        if (receiverId != null) {
            Log.d("u name id", receiverId)
        }
            val senderId=FirebaseAuth.getInstance().currentUser?.uid

        val senderRoom= receiverId+senderId
        val receiverRoom=senderId+receiverId

        Log.d("Sroom",senderRoom)


        mDbref.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object :ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Rroom",receiverRoom)
                    messageList.clear()
                    for(postSnapshot in snapshot.children){
                        val message=postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                        messageAdapter.notifyDataSetChanged()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        sendButton.setOnClickListener{
            val message =messageBox.text.toString()
            val messageObject=Message(message,senderId)
            mDbref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbref.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)

                }
            messageBox.setText("")


        }

        return view
    }


}