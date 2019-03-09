package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.ChatChannel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_chat_channel.view.*

class ChatChannelAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<ChatChannelAdapter.MyViewHolder>() {

    private var list : List<ChatChannel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_chat_channel, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val channel = list[position]
        holder.name.text = channel.channelName
        holder.itemView.setOnClickListener{listener.onItemClickListener(channel)}
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.channel_name
        val content : TextView = view.content
        val icon : CircleImageView = view.channel_icon
        val time : TextView = view.time
    }

    fun changeData(list : List<ChatChannel>){
        this.list = list
        notifyDataSetChanged()
    }

    public interface OnItemClickListener{
        fun onItemClickListener(chatChannel: ChatChannel)
    }
}