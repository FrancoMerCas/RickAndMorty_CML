package com.franco.rickandmorty_xml.ui.adapter.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.franco.rickandmorty_xml.databinding.ItemEpisodeBinding
import com.franco.rickandmorty_xml.domain.model.Episode

class EpisodeAdapter : ListAdapter<
        Episode,
        EpisodeViewHolder>(
    DiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id == newItem.id

    }
}