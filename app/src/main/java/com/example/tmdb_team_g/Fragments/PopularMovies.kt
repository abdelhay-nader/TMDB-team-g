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
import com.example.tmdb_team_g.Adapters.MoviesAdapter
import com.example.tmdb_team_g.R
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMovies : Fragment() {


    private lateinit var mainViewModel : MainViewModel


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
       mainViewModel.movieLiveData.observe(viewLifecycleOwner,{
           recyclerview_tmdb.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
           recyclerview_tmdb.adapter=  context?.let { it1 -> MoviesAdapter(it1,it) }

       })
        mainViewModel.onError.observe(viewLifecycleOwner,{
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })

        mainViewModel.loadMovieData()



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}