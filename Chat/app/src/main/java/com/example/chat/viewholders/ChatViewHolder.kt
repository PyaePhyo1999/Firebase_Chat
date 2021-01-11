package com.example.chat.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R

class ChatViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.tvName)!!
    val message = itemView.findViewById<TextView>(R.id.tvMessage)!!
}