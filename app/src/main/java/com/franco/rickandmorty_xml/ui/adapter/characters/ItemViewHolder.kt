package com.franco.rickandmorty_xml.ui.adapter.characters

import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.R
import com.franco.rickandmorty_xml.databinding.ItemCharacterBinding
import com.franco.rickandmorty_xml.ui.adapter.characters.actions.CharacterItemAction

class ItemViewHolder(
    private var binding: ItemCharacterBinding,
    private val onActions: (CharacterItemAction) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterListQuery.Result) {
        binding.apply {
            tvCharacterId.text = root.context.getString(R.string.text_id, item.id)
            tvName.text = root.context.getString(R.string.text_name, item.name)
            tvSpecie.text = root.context.getString(R.string.text_specie, item.species)

            rootCard.setOnClickListener{
                onActions(
                    CharacterItemAction.ClickSelect(item)
                )
            }
        }
    }
}