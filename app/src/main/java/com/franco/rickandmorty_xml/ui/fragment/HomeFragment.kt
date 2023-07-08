package com.franco.rickandmorty_xml.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.R
import com.franco.rickandmorty_xml.databinding.FragmentHomeBinding
import com.franco.rickandmorty_xml.ui.MainActivity
import com.franco.rickandmorty_xml.ui.adapter.characters.CharactersAdapter
import com.franco.rickandmorty_xml.ui.adapter.characters.actions.CharacterItemAction
import com.franco.rickandmorty_xml.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var mainViewModel: MainViewModel

    private val characterAdapter = CharactersAdapter { onActions(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mainViewModel = (activity as MainActivity).mainViewModel

        initUi()
        getCharacters()
    }

    private fun initUi() {
        setUpRecicler()
    }

    private fun getCharacters() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.charactersList.collect(){
                characterAdapter.submitData(it)
            }
        }
    }

    private fun setUpRecicler() {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = characterAdapter
        }
    }

    private fun onActions(action: CharacterItemAction) {
        when(action) {
            is CharacterItemAction.ClickSelect -> {
                Log.d("HomeFragment", "${action.selectCharacter}")
                setCharacter(action.selectCharacter)
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }
    }

    private fun setCharacter(selectCharacter: CharacterListQuery.Result) {
        mainViewModel.setCurrentCharacter(selectCharacter)
    }
}