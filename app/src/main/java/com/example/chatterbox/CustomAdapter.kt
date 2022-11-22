package com.example.chatterbox

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val list: ArrayList<User>):RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view= inflater.inflate(R.layout.item_view,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val str= list.get(position)
        holder.textView.text=str.name
        val bundle= Bundle()
        bundle.putString("pid",str.uid)

        holder.itemView.setOnClickListener {


            it.findNavController().navigate(R.id.action_dashBoardFragment_to_chatFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class CustomViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView) {
        val textView: TextView =ItemView.findViewById(R.id.text)

    }
}