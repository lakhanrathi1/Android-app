package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Homework
import com.vkarmaedu.vkarma.utility.getTimeDiff
import kotlinx.android.synthetic.main.list_homework_teacher.view.*

class HomeworkTeacherAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<HomeworkTeacherAdapter.MyViewHolder>() {

    private var list : List<Homework> = emptyList()

    fun changeData(list : List<Homework>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_homework_teacher, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val homework = list[position]

        holder.batch.text = homework.batch
        holder.text.text = homework.text
        holder.time.text = getTimeDiff(homework.date.time)

        if (homework.attachment == null) holder.attachment.visibility == GONE
        else holder.attachment.setOnClickListener { listener.onItemClickListener(position) }
    }

    class MyViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val batch : TextView = item.batch
        val text : TextView = item.text
        val time : TextView = item.time
        val attachment : ImageView = item.attachment
    }
}