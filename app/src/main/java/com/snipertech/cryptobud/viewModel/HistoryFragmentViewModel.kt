package com.snipertech.cryptobud.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.snipertech.cryptobud.repository.MessageRepository
import com.snipertech.cryptobud.db.entities.Message

class HistoryFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        MessageRepository(application)

    val allMessages: LiveData<List<Message>>? = repository.getAllMessages()

    fun deleteAll(){
        repository.deleteAllMessages()
    }
}