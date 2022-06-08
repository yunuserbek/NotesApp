package com.example.noteapp_k.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sunTitle") var subTitle: String,
    @ColumnInfo(name = "notes") var notes: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "priority") var priority: String
) {


}