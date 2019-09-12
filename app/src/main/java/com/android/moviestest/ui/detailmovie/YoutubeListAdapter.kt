package com.android.moviestest.ui.detailmovie

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.moviestest.R
import com.android.moviestest.util.Utils.setImageURI
import com.android.moviestest.data.trailer.Youtube
import com.android.moviestest.util.JNIUtil
import kotlinx.android.synthetic.main.item_genre_list.view.tvName
import kotlinx.android.synthetic.main.item_youtube_list.view.*

class YoutubeListAdapter(data: MutableList<Youtube>, listener: YoutubeListAdapter.Listener) :
    RecyclerView.Adapter<YoutubeListAdapter.ViewHolder>() {

    var data = mutableListOf<Youtube>()
    var listener: Listener

    init {
        this.data = data
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_youtube_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemView = holder.itemView
        val itemData = data[position]

        itemView.tvName.text = itemData.name
        itemView.ivImage.setImageURI(JNIUtil.youtubeImageURL + itemData.source + JNIUtil.youtubeImageTypeURL)

        itemView.container.setOnClickListener {
            listener.onItemClick(itemData)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener {
        fun onItemClick(itemData: Youtube)
    }
}
