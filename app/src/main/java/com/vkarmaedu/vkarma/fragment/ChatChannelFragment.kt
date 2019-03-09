package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.ChatChannelAdapter
import com.vkarmaedu.vkarma.dataModels.ChatChannel
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import kotlinx.android.synthetic.main.fragment_attendance_list.view.*

class ChatChannelFragment : Fragment(), ChatChannelAdapter.OnItemClickListener {

    private lateinit var channelAdapter : ChatChannelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat_channel, container, false)

        channelAdapter = ChatChannelAdapter(this)

        root.recycler_view.apply {
            adapter = channelAdapter
            layoutManager = LinearLayoutManager(this@ChatChannelFragment.context)
        }

        val list = ArrayList<ChatChannel>()
        list.add(ChatChannel("Student Teacher Grp","","student-teacher"))
        list.add(ChatChannel("Teacher Parents Grp","","teacher-parents"))
        list.add(ChatChannel("All Teacher Grp","","all-teachers"))
        channelAdapter.changeData(list)

        return root
    }

    override fun onItemClickListener(chatChannel: ChatChannel) {
        val fragment = ChatFragment()
        val bundle = Bundle()
        bundle.putString(MESSAGE_CHANNEL_kEY, chatChannel.channelKey)
        fragment.arguments = bundle
        replaceFragmentAddToBackStack(activity, fragment)
    }

    companion object {
        private const val MESSAGE_CHANNEL_kEY = "messageChannelKey"
    }
}
