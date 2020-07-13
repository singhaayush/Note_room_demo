package com.example.roomdemo

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomdemo.adapters.NotesAdapter
import com.example.roomdemo.db.Note
import com.example.roomdemo.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var notes:List<Note>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val button=findViewById(R.id.add_btn) as FloatingActionButton
       button.setOnClickListener { Toast.makeText(baseContext,"Hi there",Toast.LENGTH_SHORT).show()
       val intent:Intent= Intent(baseContext,AddNotes::class.java)
       startActivity(intent)}
       notes_rec_view.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        notes_rec_view.setHasFixedSize(true)
       getAllNotes()
    }
    private fun getAllNotes()
    {
        class NoteExtracter: AsyncTask<Void, Void, List<Note>>() {
            override fun doInBackground(vararg params: Void?): List<Note>? {
                notes=NoteDatabase(this@MainActivity).getnoteDao().getAllNotes()
                return notes
            }

            override fun onPostExecute(result: List<Note>?) {
                super.onPostExecute(result)

                val adapter= result?.let { NotesAdapter(it) }
                notes_rec_view.adapter=adapter
            }

        }
        NoteExtracter().execute()
    }

}