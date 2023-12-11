package com.example.todopr.domain.di

import android.content.Context
import androidx.room.Room
import com.example.todopr.domain.dao.NoteDao
import com.example.todopr.domain.database.NoteDatabase
import com.example.todopr.domain.repository.NoteRepositoryImpl
import com.example.todopr.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : NoteDatabase{
        return Room
            .databaseBuilder(context,
                NoteDatabase::class.java,
                "note_DB")
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(noteDatabase: NoteDatabase):Repository{
        return NoteRepositoryImpl(noteDao = noteDatabase.noteDao())
    }

}