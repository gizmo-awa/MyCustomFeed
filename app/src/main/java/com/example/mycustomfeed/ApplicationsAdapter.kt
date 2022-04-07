package com.example.mycustomfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//APPLICATIONSADAPTER RECIBE UN ARREGLO DE OBJETOS FEEDENTRY, LO METE EN LAS TARJETAS DE FEEDNOTICIAS Y LO LLEVA AL RECYCLER DE RSSNEWS

class ApplicationsAdapter (context: Context, feedEntries: ArrayList<FeedEntry>):
RecyclerView.Adapter<ApplicationsAdapter.ViewHolder>() {
    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<FeedEntry>? = null
    init {
        localContext = context
        localFeedEntries = feedEntries
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplicationsAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View = layoutInflater.inflate(R.layout.feed_noticias, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicationsAdapter.ViewHolder, position: Int) {
        val currentFeedEntry: FeedEntry = localFeedEntries!![position]
        holder.textTitle.text = currentFeedEntry.title
        holder.textLink.text = currentFeedEntry.link
        holder.textSource.text = currentFeedEntry.source
    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?: 0
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val textTitle: TextView = v.findViewById(R.id.textViewTitle)
        val textLink: TextView = v.findViewById(R.id.textViewLink)
        val textSource: TextView = v.findViewById(R.id.textViewSource)
    }
}