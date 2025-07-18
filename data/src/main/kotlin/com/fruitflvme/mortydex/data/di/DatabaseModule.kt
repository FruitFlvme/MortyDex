package com.fruitflvme.mortydex.data.di

import androidx.room.Room
import com.fruitflvme.mortydex.data.database.AppDatabase
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, "morty-dex-db").build()
        }
        single { get<AppDatabase>().characterDao() }
        single { get<AppDatabase>().remoteKeyDao() }
    }
}