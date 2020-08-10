package com.lamine.notepad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)
        create_note_fab.setOnClickListener(this)

        notes = mutableListOf<Note>()
        notes.add(Note("Note 1", "Je suis un junior developer !"))
        notes.add(Note("Note lamine", "Je suis un junior developer !"))
        notes.add(Note("Note julie", "Je suis un junior developer !"))
        notes.add(Note("Note Aya ", "Je suis un junior developer !"))

        adapter = NoteAdapter(notes, this)

        val recyclerView = notes_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }
        when (requestCode) {
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)
        when (data.action) {
            NoteDetailActivity.ACTION_SAVE_NOTE -> {
                val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
                saveNote(note, noteIndex)
            }
            NoteDetailActivity.ACTION_DELETE_NOTE -> {
                deleteNote(noteIndex)
            }
        }
    }

    private fun deleteNote(noteIndex: Int) {
        if (noteIndex < 0) {
            return
        }
        val note = notes.removeAt(noteIndex)
        adapter.notifyDataSetChanged()
    }


    override fun onClick(v: View) {
        if (v.tag != null) {
            // Log.i("NoteListActivity", "Click sur un item note de la list !!")
            showNoteDetail(v.tag as Int)
        } else {
            when (v.id) {
                R.id.create_note_fab -> createNewNote()
            }

        }
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }


    fun saveNote(note: Note, noteIndex: Int) {
        if (noteIndex < 0) {
            notes.add(0, note)
        } else {
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = if (noteIndex < 0) Note() else notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)

    }
}