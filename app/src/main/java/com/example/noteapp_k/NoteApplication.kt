package com.example.noteapp_k

import android.app.Application
import com.example.oteapp_k.Database.NoteDatabase

class NoteApplication : Application() {
    val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}