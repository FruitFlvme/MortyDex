package com.fruitflvme.mortydex.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val id: String,
    val nextPage: Int?
)