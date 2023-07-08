package com.franco.rickandmorty_xml.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.franco.rickandmorty_xml.R
import com.franco.rickandmorty_xml.data.utils.SingleCharacterState
import com.franco.rickandmorty_xml.databinding.FragmentDetailsBinding
import com.franco.rickandmorty_xml.domain.model.InfoCharacter
import com.franco.rickandmorty_xml.ui.MainActivity
import com.franco.rickandmorty_xml.ui.adapter.episode.EpisodeAdapter
import com.franco.rickandmorty_xml.ui.utils.loadThumbnail
import com.franco.rickandmorty_xml.ui.viewmodel.MainViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private lateinit var mainViewModel: MainViewModel

    private val episodeAdapter = EpisodeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        mainViewModel = (activity as MainActivity).mainViewModel

        observeState()
        setUpRecicler()
        getSingleCharacterExec()
    }

    private fun observeState() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.singleCharacterState.collect { state ->
                when (state) {
                    is SingleCharacterState.Loading -> {
                        // Mostrar una animación de carga o un indicador de progreso
                    }
                    is SingleCharacterState.Success -> {
                        val character = state.character
                        Log.d("DetailsFragment", "Character -> $character")
                        charcheInfo(character)
                        episodeAdapter.submitList(character.episode)
                    }
                    is SingleCharacterState.Error -> {
                        val exception = state.exception
                        Log.e("DetailsFragment", "Error: $exception")
                    }
                }
            }
        }
    }

    private fun getSingleCharacterExec() {
        mainViewModel.getSingleCharacter()
    }

    private fun charcheInfo(character: InfoCharacter) {
        binding.apply {
            tvNameCharacter.text = character.name
            character.image?.let { imageURL ->
                ivCharacter.loadThumbnail(
                    imageURL,
                    20f,
                    requireContext()
                )
            }
            tvStatus.text = character.status
            tvGender.text = character.gender
            tvOriginName.text = getString(R.string.text_origin_name,character.origin?.name)
            tvOriginType.text = getString(R.string.text_origin_type, character.origin?.type)
            tvOriginDimension.text = getString(R.string.text_origin_dimension, character.origin?.dimension)
        }
    }

    private fun setUpRecicler() {
        binding.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = episodeAdapter
        }
    }
}