package com.android.moviestest.ui.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.moviestest.R
import com.android.moviestest.data.genre.Genre
import kotlinx.android.synthetic.main.item_genre_list.view.*

class GenreListAdapter(data: MutableList<Genre>) : RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {

    var data = mutableListOf<Genre>()

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemView = holder.itemView
        val itemData = data[position]

        itemView.tvName.text = itemData.name

        itemView.setOnClickListener {
            val bundle = bundleOf(
                "id" to itemData.id
            )
            Navigation.findNavController(itemView).navigate(R.id.movieListFragment, bundle)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
