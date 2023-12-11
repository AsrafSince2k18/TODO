package com.example.todopr.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todopr.domain.dao.NoteDao
import com.example.todopr.domain.data.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}