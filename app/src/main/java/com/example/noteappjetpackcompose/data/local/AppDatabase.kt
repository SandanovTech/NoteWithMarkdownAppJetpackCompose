package com.example.noteappjetpackcompose.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.example.noteappjetpackcompose.data.local.dao.NoteDao
import com.example.noteappjetpackcompose.data.local.model.NoteEntity

@Database(entities = [NoteEntity::class], exportSchema = true ,version = 2 ,autoMigrations = [AutoMigration(1 , 2,  spec = AppDatabase.MyAutoMigration::class)])
@TypeConverters(NoteConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    @RenameColumn("note", "inputText", "mainDescription")
    class MyAutoMigration : AutoMigrationSpec
}