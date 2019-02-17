package com.vkarmaedu.vkarma.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.AttendanceListAdapter
import com.vkarmaedu.vkarma.dataModels.Attendance
import com.vkarmaedu.vkarma.utility.RecyclerViewItemTouchHelper
import kotlinx.android.synthetic.main.fragment_attendance_list.view.*

class AttendanceListFragment : Fragment() {

    private val attendanceList = ArrayList<Attendance>()
    private lateinit var attendanceAdapter: AttendanceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_attendance_list, container, false)

        val list = arguments?.getParcelableArrayList<Attendance>("1")
        if (list != null) attendanceAdapter = AttendanceListAdapter(list)

        root.recycler_view.apply {
            adapter = attendanceAdapter
            layoutManager = LinearLayoutManager(context)
        }

        ItemTouchHelper( RecyclerViewItemTouchHelper() ).attachToRecyclerView(root.recycler_view)

        return root
    }

}
