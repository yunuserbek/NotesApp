package com.example.noteapp_k

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_k.databinding.ItemNotesBinding
import com.example.noteapp_k.model.Note

class NotesAdapter(private var notesList:List<Note>):RecyclerView.Adapter<NotesAdapter.NotesHolder>() {
    class NotesHolder(val itemNotesBinding: ItemNotesBinding):RecyclerView.ViewHolder(itemNotesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val itemCardBinding = ItemNotesBinding.inflate(layoutInflater,parent,false)
        return NotesHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val data = notesList[position]
        holder.itemNotesBinding.notesTitle.text = data.title
        holder.itemNotesBinding.notessubTitle.text = data.subTitle
        holder.itemNotesBinding.notesDate.text = data.date
        when(data.priority){
            "1" ->{holder.itemNotesBinding.notesPriority.setBackgroundResource(R.drawable.green_shape)}
            "2" ->{holder.itemNotesBinding.notesPriority.setBackgroundResource(R.drawable.yellow_shape)}
            "3" ->{holder.itemNotesBinding.notesPriority.setBackgroundResource(R.drawable.red_shape)}
        }


    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}