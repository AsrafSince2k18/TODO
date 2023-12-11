package com.example.todopr.domain.repository

import com.example.todopr.domain.data.Notes
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addNote(notes: Notes)

    suspend fun deleteNote(notes: Notes)

    suspend fun getNote(id : Int):Notes?

    fun getAllNote() : Flow<List<Notes>>

}