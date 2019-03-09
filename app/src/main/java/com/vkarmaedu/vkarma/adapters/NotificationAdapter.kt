package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Notification
import com.vkarmaedu.vkarma.utility.getTimeDiff
import kotlinx.android.synthetic.main.list_notifications.view.*

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {
    private var list : List<Notification> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_notifications, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notification = list[position]
        holder.heading.text = notification.heading
        holder.content.text = notification.content
        holder.time.text = getTimeDiff(notification.timeStamp)
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val heading = view.heading
        val content = view.content
        val time = view.time
    }
}