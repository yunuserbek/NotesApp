package com.example.noteapp_k


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp_k.model.Note
import com.example.noteapp_k.model.NotesDao
import kotlinx.coroutines.launch

class NoteViewModel(private val noteDao: NotesDao) : ViewModel() {


    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    val getHighNotes: LiveData<List<Note>> = noteDao.getHighNotes()
    val getMediumNotes: LiveData<List<Note>> = noteDao.getMediumNotes()
    val getLowNotes: LiveData<List<Note>> = noteDao.getLowNotes()


    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            noteDao.update(note)
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
        }

    }




    class NoteViewModelFactory(private val itemDao: NotesDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}