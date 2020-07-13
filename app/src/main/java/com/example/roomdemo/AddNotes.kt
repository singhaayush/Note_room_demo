package com.example.roomdemo

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.roomdemo.db.Note
import com.example.roomdemo.db.NoteDatabase
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.activity_main.*

class AddNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        done_btn.setOnClickListener(View.OnClickListener {
            val title = title_input.text.toString()
            val body = note_body_input.text.toString()

            if (title.isNullOrEmpty()) {
                title_input.error = "title_required"
                return@OnClickListener
            }

            if (body.isNullOrEmpty()) {
                note_body_input.error="Body required"
                return@OnClickListener
            }

            val note= Note(title,body)
            saveNote(note,this)

        })
    }
    private fun saveNote(note: Note, context: Activity)
    {
        class AddNoteThread : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {

                NoteDatabase(context).getnoteDao().addNote(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(baseContext,"Note Added",Toast.LENGTH_SHORT).show()

            }


        }
        AddNoteThread().execute()
    }
}