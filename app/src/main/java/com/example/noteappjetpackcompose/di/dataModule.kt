package com.example.noteappjetpackcompose.di

import androidx.room.Room
import com.example.noteappjetpackcompose.data.RepositoryImpl
import com.example.noteappjetpackcompose.data.local.AppDatabase
import com.example.noteappjetpackcompose.data.local.repository.LocalDataSourceImpl
import com.example.noteappjetpackcompose.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "application_db")
            .build()
    }
    single { get<AppDatabase>().noteDao() }
    single<Repository> { RepositoryImpl(get()) }
    single { LocalDataSourceImpl(get()) }
}