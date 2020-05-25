package com.snipertech.cryptobud.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
class Message(
    val message: String,
    val key: String,
    val type: String,
    val algo: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
