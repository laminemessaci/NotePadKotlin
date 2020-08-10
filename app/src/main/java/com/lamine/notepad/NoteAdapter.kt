package com.lamine.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

/**
 *Created by Lamine MESSACI on 10/08/2020.
 */
class NoteAdapter(val notes: List<Note>, val itemClickListener: View.OnClickListener)

    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val cardView = itemView.card_view
        val titleView: TextView? = itemView.title
        val excerptView: TextView? = itemView.excerpt


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val note = notes[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView!!.text = note.title
        holder.excerptView!!.text = note.text
    }
    override fun getItemCount(): Int {
       return notes.size
    }




}