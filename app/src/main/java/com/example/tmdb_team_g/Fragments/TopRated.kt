package com.example.tmdb_team_g.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.tmdb_team_g.MainViewModel
import com.example.tmdb_team_g.R

class TopRated : Fragment() {


    private lateinit var mainViewModel : MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.topMovieLiveData.observe(viewLifecycleOwner,{

        })

        mainViewModel.onError.observe(viewLifecycleOwner,{
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        })

        mainViewModel.loadTopMovieData()


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}