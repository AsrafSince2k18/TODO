package com.example.todopr.domain.repository

import com.example.todopr.domain.dao.NoteDao
import com.example.todopr.domain.data.Notes
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao : NoteDao) : Repository {
    override suspend fun addNote(notes: Notes) {
        noteDao.insertNote(notes)
    }

    override suspend fun deleteNote(notes: Notes) {
        noteDao.deleteNote(noteId = notes)
    }

    override suspend fun getNote(id: Int): Notes? {
        return noteDao.getPatientId(id)
    }

    override fun getAllNote(): Flow<List<Notes>> {
        return noteDao.allNote()
    }
}