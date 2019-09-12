package com.android.moviestest.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.moviestest.R
import com.android.moviestest.util.Utils.setImageURI
import com.android.moviestest.data.movie.Movie
import com.android.moviestest.util.JNIUtil
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter(data: MutableList<Movie>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var data = mutableListOf<Movie>()

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemView = holder.itemView
        val itemData = data[position]

        itemView.ivImage.setImageURI(JNIUtil.mediaURL + itemData.posterPath)
        itemView.tvName.text = itemData.title
        itemView.tvRelease.text = itemData.releaseDate
        itemView.tvVote.text = itemData.voteAverage.toString()
        itemView.tvOverview.text = itemData.overview

        itemView.setOnClickListener {
            val bundle = bundleOf(
                "id" to itemData.id
            )
            Navigation.findNavController(itemView).navigate(R.id.movieDetailFragment, bundle)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
