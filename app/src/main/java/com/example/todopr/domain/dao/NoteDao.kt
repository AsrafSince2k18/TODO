package com.example.todopr.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.todopr.domain.data.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(noteId : Notes)

    @Query("SELECT * FROM note_db")
    fun allNote() : Flow<List<Notes>>

    @Query("SELECT * FROM note_db WHERE patientsId IN (:id)")
    suspend fun getPatientId(id : Int) : Notes?

}