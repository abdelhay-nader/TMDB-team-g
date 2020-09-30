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
import com.example.tmdb_team_g.Adapters.MoviesAdapter
import com.example.tmdb_team_g.R
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMovies : Fragment() {


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


        recyclerview_tmdb.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerview_tmdb.canScrollVertically(1))
                    mainViewModel.loadNextMoviePage()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })


        buttonDown.setOnClickListener {

            if(i==1) recyclerview_tmdb.smoothScrollToPosition(28 + s)
            if(i==2) recyclerview_tmdb.smoothScrollToPosition(28 + s)
            if(i in 3..500) {

                s=s+20
                recyclerview_tmdb.smoothScrollToPosition(28 + s)

            }

            i++
        }






        super.onViewCreated(view, savedInstanceState)
    }


}