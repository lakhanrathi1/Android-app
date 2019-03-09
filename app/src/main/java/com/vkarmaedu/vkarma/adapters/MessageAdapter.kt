package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.data.Message
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.utility.timeFormat
import kotlinx.android.synthetic.main.list_homework_teacher.view.*
import kotlinx.android.synthetic.main.message_recieve.view.*

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messageList: List<Message> = emptyList()

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = messageList[position]
        return when(item.senderName){
            UserRepo.name -> {
                if(item.attachment == null) VIEW_TYPE_MESSAGE_SENT else VIEW_TYPE_IMAGE_MESSAGE_SENT
            }
            else -> {
                if(item.attachment == null) VIEW_TYPE_MESSAGE_RECEIVED else VIEW_TYPE_IMAGE_MESSAGE_RECEIVED
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                ViewHolderRecieve( LayoutInflater.from(parent.context).inflate(R.layout.message_recieve,
                                parent, false))
            }
            VIEW_TYPE_MESSAGE_SENT -> {
                ViewHolderSend(LayoutInflater.from(parent.context).inflate(R.layout.message_send,
                                parent, false))
            }
            VIEW_TYPE_IMAGE_MESSAGE_SENT -> {
                ViewHolderImageSend(LayoutInflater.from(parent.context).inflate(R.layout.message_image_send,
                    parent, false))
            }
            else -> {
                ViewHolderImageRecieve(LayoutInflater.from(parent.context).inflate(R.layout.message_image_recieve,
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
                holder.time.text = timeFormat.format(message.timeStamp?.time)
            }
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                holder as ViewHolderRecieve
                holder.userName.text = message.senderName
                holder.text.text = message.text
                holder.time.text = timeFormat.format(message.timeStamp?.time)
            }
            VIEW_TYPE_IMAGE_MESSAGE_RECEIVED -> {
                holder as ViewHolderImageRecieve
                holder.userName.text = message.senderName
                holder.time.text = timeFormat.format(message.timeStamp?.time)
                Glide.with(holder.itemView).load(message.attachment).into(holder.attachment)
            }
            VIEW_TYPE_IMAGE_MESSAGE_SENT -> {
                holder as ViewHolderImageSend
                holder.time.text = timeFormat.format(message.timeStamp?.time)
                Glide.with(holder.itemView).load(message.attachment).into(holder.attachment)
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

    class ViewHolderImageRecieve(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.message_username
        val attachment: ImageView = itemView.attachment
        val time: TextView = itemView.message_time
    }

    class ViewHolderImageSend(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val attachment: ImageView = itemView.attachment
        val time: TextView = itemView.message_time
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
        private const val VIEW_TYPE_IMAGE_MESSAGE_SENT = 3
        private const val VIEW_TYPE_IMAGE_MESSAGE_RECEIVED = 4

    }
}