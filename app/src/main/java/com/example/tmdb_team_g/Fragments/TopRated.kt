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
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb_team_g.ViewModel.MainViewModel
import com.example.tmdb_team_g.R
import com.example.tmdb_team_g.Adapters.TopMoviesAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import kotlinx.android.synthetic.main.fragment_top_rated.buttonDown

class TopRated : Fragment() {


    val x = 10
    var s = x

    var i : Int = 1



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


        myRecycleView_Top_API_Movie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!myRecycleView_Top_API_Movie.canScrollVertically(1))
                    mainViewModel.loadNextTopMoviePage()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })


        buttonDown.setOnClickListener {


            if(i==1) myRecycleView_Top_API_Movie.smoothScrollToPosition(28 + s)
            if(i==2) myRecycleView_Top_API_Movie.smoothScrollToPosition(28 + s)
            if(i in 3..500) {

                s=s+20
                myRecycleView_Top_API_Movie.smoothScrollToPosition(28 + s)

            }

            i++
        }






        super.onViewCreated(view, savedInstanceState)
    }



}