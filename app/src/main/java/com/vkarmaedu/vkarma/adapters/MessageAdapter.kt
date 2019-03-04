package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.data.Message
import com.vkarmaedu.vkarma.data.UserRepo
import kotlinx.android.synthetic.main.message_recieve.view.*
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messageList: List<Message> = emptyList()

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(messageList[position].userName){
            UserRepo.name -> VIEW_TYPE_MESSAGE_SENT
            else -> VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                ViewHolderRecieve( LayoutInflater.from(parent.context).inflate(
                    R.layout.message_recieve,
                                parent, false))
            }
            else -> {
                ViewHolderSend(LayoutInflater.from(parent.context).inflate(R.layout.message_send,
                                parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        when(holder.itemViewType){
            VIEW_TYPE_MESSAGE_SENT -> {
                holder as ViewHolderSend
                holder.text.text = message.text
                holder.time.text = SimpleDateFormat("hh:mm", Locale.getDefault()).format(message.timeStamp?.time)
            }
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                holder as ViewHolderRecieve
                holder.userName.text = message.userName
                holder.text.text = message.text
                holder.time.text = SimpleDateFormat("hh:mm", Locale.getDefault()).format(message.timeStamp?.time)
            }
        }
    }

    fun changeData(list : List<Message>){
        messageList = list
        notifyDataSetChanged()
    }

    class ViewHolderRecieve(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.message_username
        val text: TextView = itemView.message_text
        val time: TextView = itemView.message_time
    }

    class ViewHolderSend(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.message_text
        val time: TextView = itemView.message_time
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }
}