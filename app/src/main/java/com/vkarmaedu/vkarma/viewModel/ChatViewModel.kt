package com.vkarmaedu.vkarma.viewModel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.vkarmaedu.vkarma.data.Message
import com.vkarmaedu.vkarma.data.MessageDatabase
import com.vkarmaedu.vkarma.data.MessageRepository
import com.vkarmaedu.vkarma.data.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    val allMessages : LiveData<List<Message>>
    private val messageRepo : MessageRepository
    private val storageRef = FirebaseStorage.getInstance().getReference("chat_attachments")
    private val messageRef = FirebaseDatabase.getInstance().getReference("Institute/1/Messages/class1")
    private val listener = object : ChildEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {

        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {

        }
        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val message : Message? = snapshot.getValue(Message::class.java)
            if (message != null){
                insertLocalDatabase(message)
            }
        }
        override fun onChildRemoved(p0: DataSnapshot) {

        }
    }

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val dao = MessageDatabase.getDatabase(application).messageDao()
        messageRepo = MessageRepository(dao)
        allMessages = messageRepo.allMessages
        messageRef.addChildEventListener(listener)
    }

    fun insertLocalDatabase (message: Message) = scope.launch(Dispatchers.IO) {
        messageRepo.insert(message)
    }

    fun insertFirebase(message: Message){
        messageRef.push().setValue(message)
    }

    fun insertStorage(uri: Uri){
        val fileReference = storageRef.child("${System.currentTimeMillis()}")
        val uploadTask = fileReference.putFile(uri)

        uploadTask.continueWithTask {
            if (!it.isSuccessful){
                it.exception?.let {
                    throw it
                }
            }
            fileReference.downloadUrl
        }.addOnCompleteListener {
            if (it.isSuccessful){
                val downloadUri = it.result.toString()
                insertFirebase(Message(UserRepo.name!!, "", Date(System.currentTimeMillis()), downloadUri))
                Log.d(TAG, "success : $downloadUri")
            }
            else{
                Log.d(TAG , "upload failed : ${it.exception}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
        messageRef.removeEventListener(listener)
    }

    companion object {
        private const val TAG = "ChatViewModel"
    }
}