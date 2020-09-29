package com.example.tmdb_team_g.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb_team_g.ViewModel.MainViewModel
import com.example.tmdb_team_g.R
import com.example.tmdb_team_g.Adapters.TopMoviesAdapter
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRated : Fragment() {


    private lateinit var mainViewModel : MainViewModel



    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.topMovieLiveData.observe(viewLifecycleOwner,{

            myRecycleView_Top_API_Movie.layoutManager = LinearLayoutManager(context,
                LinearLayout.VERTICAL,false)

            myRecycleView_Top_API_Movie.adapter = context?.let { it1 -> TopMoviesAdapter(it1,it) }

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