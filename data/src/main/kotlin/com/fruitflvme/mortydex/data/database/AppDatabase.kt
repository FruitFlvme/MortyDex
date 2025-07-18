package com.fruitflvme.mortydex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fruitflvme.mortydex.data.database.dao.CharacterDao
import com.fruitflvme.mortydex.data.database.dao.RemoteKeyDao
import com.fruitflvme.mortydex.data.database.entity.CharacterEntity
import com.fruitflvme.mortydex.data.database.entity.RemoteKey

@Database(
    entities = [CharacterEntity::class, RemoteKey::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}