package com.example.chatterbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_SENT=1
    val ITEM_RECEIVE=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            val inflater= LayoutInflater.from(parent.context)
            val view= inflater.inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
        else{

            val inflater=LayoutInflater.from(parent.context)
            val view= inflater.inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage=messageList[position]
        if(holder.javaClass == SentViewHolder::class.java){

            val viewHolder=holder as SentViewHolder
            holder.sentMessage.text=currentMessage.message
        }
        else{
            val viewHolder=holder as ReceiveViewHolder
            holder.receiveMessage.text=currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return  messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentList=messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentList.senderID)){
            return ITEM_SENT
        }
        else{
            return  ITEM_RECEIVE
        }


    }

    class  SentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sentMessage=itemView.findViewById<TextView>(R.id.sent_mess)

    }

    class  ReceiveViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val receiveMessage=itemView.findViewById<TextView>(R.id.receive_mess)
    }
}