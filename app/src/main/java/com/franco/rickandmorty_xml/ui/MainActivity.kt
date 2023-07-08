package com.franco.rickandmorty_xml.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.franco.rickandmorty_xml.R
import com.franco.rickandmorty_xml.ui.viewmodel.MainViewModel
import com.franco.rickandmorty_xml.ui.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            mainViewModelFactory
        ).get(MainViewModel::class.java)
    }
}