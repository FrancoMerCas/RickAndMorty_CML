package com.franco.rickandmorty_xml.ui.adapter.episode

import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmorty_xml.R
import com.franco.rickandmorty_xml.databinding.ItemEpisodeBinding
import com.franco.rickandmorty_xml.domain.model.Episode

class EpisodeViewHolder(
    private var binding: ItemEpisodeBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(item: Episode) {
        binding.apply {
            tvIdEpisode.text = root.context.getString(R.string.text_id_episode, item.id)
            tvNameEpisode.text = item.name
        }
    }
}