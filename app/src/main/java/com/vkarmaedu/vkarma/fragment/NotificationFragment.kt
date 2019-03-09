package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.NotificationAdapter
import com.vkarmaedu.vkarma.data.Notification
import com.vkarmaedu.vkarma.viewModel.NotificationViewmodel
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*

class NotificationFragment : Fragment(), NotificationAdapter.OnItemClickListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(NotificationViewmodel::class.java) }
    private val myAdapter by lazy { NotificationAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notification, container, false)

        root.recycler_view.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@NotificationFragment.context)
        }

        viewModel.allNotification.observe(this , Observer {
            myAdapter.changeData(it)
            if (it.isEmpty())changeVisibility(false) else changeVisibility(true)
        })

        return root
    }

    fun changeVisibility(bool : Boolean){
        recycler_view.visibility = if (bool) View.VISIBLE else View.GONE
        no_notifications.visibility = if (bool) View.GONE else View.VISIBLE
    }

    override fun onItemClickListener(notification: Notification) {
        viewModel.delete(notification)
    }

    companion object {
        private const val TAG = "NotificationFragment"
    }
}
