package com.example.noteappjetpackcompose.di

import androidx.room.Room
import com.example.noteappjetpackcompose.data.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "application_db")
            .build()
    }
}