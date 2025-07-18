package com.fruitflvme.mortydex.data.di

import com.fruitflvme.mortydex.data.network.RickAndMortyApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkModule {
    val module = module {
        single {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(RickAndMortyApi::class.java)
        }
    }
}