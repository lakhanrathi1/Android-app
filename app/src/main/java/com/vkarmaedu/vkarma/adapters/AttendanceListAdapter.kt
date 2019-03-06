package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Attendance
import kotlinx.android.synthetic.main.list_attendance.view.*

class AttendanceListAdapter(private val list : List<Attendance>) : RecyclerView.Adapter<AttendanceListAdapter.AttendanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_attendance, parent, false)
        return AttendanceViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = list[position]
        holder.id.text = attendance.UId
        holder.name.text = attendance.name
        holder.attendance.text = if(attendance.present) "present" else "absent"
    }

    class AttendanceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val id : TextView = itemView.attendance_id
        val name : TextView = itemView.attendance_name
        val attendance : TextView = itemView.attendance_attendance
    }
}