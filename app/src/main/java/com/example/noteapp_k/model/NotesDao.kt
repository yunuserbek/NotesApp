package com.example.noteapp_k.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes:Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notesTable order by id ASC")
     fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notesTable WHERE priority=1")
    fun getHighNotes(): LiveData<List<Note>>
    @Query("SELECT * FROM notesTable WHERE priority=2")
    fun getMediumNotes(): LiveData<List<Note>>
    @Query("SELECT * FROM notesTable WHERE priority=3")
    fun getLowNotes(): LiveData<List<Note>>




}