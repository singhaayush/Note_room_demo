package com.example.roomdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.adapters.NotesAdapter.NoteViewHolder
import com.example.roomdemo.db.Note
import kotlinx.android.synthetic.main.activity_add_notes.view.*
import kotlinx.android.synthetic.main.custom_layout_notes.view.*

class NotesAdapter(private val notes:List<Note>):RecyclerView.Adapter<NoteViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.custom_layout_notes,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount()=notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.notes_title.text=notes[position].title
        holder.itemView.notes_body.text=notes[position].body
    }
    class NoteViewHolder(view:View):RecyclerView.ViewHolder(view)
}