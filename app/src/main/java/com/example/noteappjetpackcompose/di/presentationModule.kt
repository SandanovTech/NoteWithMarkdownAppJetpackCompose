package com.example.noteappjetpackcompose.di

import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import com.example.noteappjetpackcompose.presentation.viewModel.NoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { NoteViewModel(get()) }
}