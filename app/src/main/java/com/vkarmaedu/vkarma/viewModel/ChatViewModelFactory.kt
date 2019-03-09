@file:Suppress("UNCHECKED_CAST")

package com.vkarmaedu.vkarma.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatViewModelFactory(val application: Application, val value : String) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(application, value) as T
    }
}