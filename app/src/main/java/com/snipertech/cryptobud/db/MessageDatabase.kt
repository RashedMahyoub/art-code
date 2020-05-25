package com.snipertech.cryptobud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.snipertech.cryptobud.db.dao.MessageDao
import com.snipertech.cryptobud.db.entities.Message

@Database(entities = [Message::class], version = 1)
abstract class MessageDatabase: RoomDatabase(){

    abstract fun messageDao(): MessageDao

    companion object {
        var INSTANCE: MessageDatabase? = null
        fun getDatabaseInstance(context: Context): MessageDatabase? {
            if (INSTANCE == null) {
                synchronized(MessageDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDatabase::class.java,
                        "message_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyDataBase(){
        INSTANCE = null
    }
}