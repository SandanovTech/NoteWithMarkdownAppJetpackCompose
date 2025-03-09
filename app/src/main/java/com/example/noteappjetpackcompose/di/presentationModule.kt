package com.example.noteappjetpackcompose.di

import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    single { MainViewModel(get()) }
}