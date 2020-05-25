package com.snipertech.cryptobud.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.snipertech.cryptobud.db.dao.MessageDao
import com.snipertech.cryptobud.db.entities.Message
import com.snipertech.cryptobud.db.MessageDatabase

class MessageRepository(application: Application) {

    private var messageDatabase = MessageDatabase.getDatabaseInstance(
        application
    )?.messageDao()!!

    fun insert(message: Message){
        InsertMessageAsyncTask(
            messageDatabase
        ).execute(message)
    }

    fun deleteAllMessages(){
        DeleteMessagesAsyncTask(
            messageDatabase
        ).execute()
    }

    fun getAllMessages(): LiveData<List<Message>>? {
        return messageDatabase.getAllMessages()
    }

    private class InsertMessageAsyncTask internal constructor(private val messageDao: MessageDao) :
        AsyncTask<Message, Void, Void>() {

        override fun doInBackground(vararg params: Message): Void? {
            messageDao.insert(params[0])
            return null
        }
    }

    private class DeleteMessagesAsyncTask internal constructor(private val messageDao: MessageDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            messageDao.deleteAllMessages()
            return null
        }
    }
}