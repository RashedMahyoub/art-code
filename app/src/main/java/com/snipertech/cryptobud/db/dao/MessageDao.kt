package com.snipertech.cryptobud.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.snipertech.cryptobud.db.entities.Message

@Dao
interface MessageDao {
    @Insert
    fun insert(message: Message)

    @Query("DELETE FROM message_table")
    fun deleteAllMessages()

    @Query("SELECT * FROM message_table")
    fun getAllMessages(): LiveData<List<Message>>
}