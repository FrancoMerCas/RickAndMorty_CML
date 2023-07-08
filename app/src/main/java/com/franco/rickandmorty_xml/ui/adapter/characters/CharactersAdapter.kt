package com.franco.rickandmorty_xml.ui.adapter.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.databinding.ItemCharacterBinding
import com.franco.rickandmorty_xml.ui.adapter.characters.actions.CharacterItemAction

class CharactersAdapter(
    private val onActions: (CharacterItemAction) -> Unit
):
    PagingDataAdapter<
            CharacterListQuery.Result,
            ItemViewHolder>(DiffUtilCallBack())
{

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ItemViewHolder(binding, onActions)
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterListQuery.Result>() {
        override fun areItemsTheSame(
            oldItem: CharacterListQuery.Result,
            newItem: CharacterListQuery.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterListQuery.Result,
            newItem: CharacterListQuery.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}