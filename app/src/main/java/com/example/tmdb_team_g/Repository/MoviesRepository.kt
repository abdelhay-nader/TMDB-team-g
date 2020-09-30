package com.example.tmdb_team_g.Repository


import android.content.Context
import com.example.tmdb_team_g.Database.AppDatabase
import com.example.tmdb_team_g.Network.ApiServices
import com.example.tmdb_team_g.Network.RetrofitClient
import com.example.tmdb_team_g.Response.MovieResponse
import com.example.tmdb_team_g.Response.TopMovieResponse
import com.example.tmdb_team_g.Response.resultsList
import com.example.tmdb_team_g.Response.resultsList2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MoviesRepository {


    private val apiServices : ApiServices by lazy{

        RetrofitClient.getClient().create(ApiServices::class.java)

    }


    private lateinit var appDatabase: AppDatabase


    private lateinit var movieData : List<resultsList>
    private lateinit var topMovieData :List<resultsList2>

    fun requestMovieData (callBack : MovieCallback) {



        apiServices.getMovieByApi("099a5418f2bec70523fe74e21f45b456",page=1).
        enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                println("OnResponseCalled")
                if (response.isSuccessful){
                    movieData = response.body()!!.results

                    appDatabase.movieDao().addMovies(movieData)

                    callBack.onMovieReady(movieData)
                } else if (response.code() in 400..404){
                    val msg = "Simple Error, please try again"
                    callBack.onMovieLoadingError(msg)
                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //println(t.message)
                //call.cancel()
                val msg = "Error while getting Movie Data"
                callBack.onMovieLoadingError(msg)

                callBack.onMovieReady(appDatabase.movieDao().getAllMovies())

            }
        })


    }




    fun requestMovieData2 (movieDataaa: ArrayList<resultsList>, callBack : MovieCallback, page1:Int ) {




        if (page1 == 501) {callBack.onMovieReady(movieDataaa)}
        else {

            var page: Int = page1

            apiServices.getMovieByApi("099a5418f2bec70523fe74e21f45b456", page = page)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        println("OnResponseCalled")
                        if (response.isSuccessful) {
                            movieData = response.body()!!.results
                            movieDataaa.plusAssign(movieData)
                            appDatabase.movieDao().addMovies(movieData)
                            callBack.onMovieReady(movieDataaa)
                        } else if (response.code() in 400..404) {
                            val msg = "Simple Error, please try again"
                            callBack.onMovieLoadingError(msg)
                        }

                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        //println(t.message)
                        //call.cancel()
                        val msg = "Error while getting Movie Data"
                        callBack.onMovieLoadingError(msg)
                        callBack.onMovieReady(
                            appDatabase.movieDao().getAllMovies() as ArrayList<resultsList>
                        )
                    }
                })

        }

    }





    fun requestTopMovieData(callBack : MovieCallback){

        apiServices.getTopMovieByApi("099a5418f2bec70523fe74e21f45b456",page=1).
        enqueue(object : Callback<TopMovieResponse> {
            override fun onResponse(
                call: Call<TopMovieResponse>,
                response: Response<TopMovieResponse>
            ) {
                if (response.isSuccessful){
                    topMovieData = response.body()!!.results

                    appDatabase.topMovieDao().addTopMovies(topMovieData)

                    callBack.onTopMovieReady(topMovieData)
                } else if (response.code() in 400..404){
                    val msg = "Simple Error, please try again"
                    callBack.onMovieLoadingError(msg)
                }




            }

            override fun onFailure(call: Call<TopMovieResponse>, t: Throwable) {

                val msg = "Error while getting Movie Data"
                callBack.onMovieLoadingError(msg)

                callBack.onTopMovieReady(appDatabase.topMovieDao().getAllTopMovies())


            }

        })


    }


    fun requestTopMovieData2(movieDataaaaaa: ArrayList<resultsList2>, callBack : MovieCallback, page1:Int){

        var page: Int = page1



        if (page1 == 391) {callBack.onTopMovieReady(movieDataaaaaa)}
        else {

            apiServices.getTopMovieByApi("099a5418f2bec70523fe74e21f45b456", page = page)
                .enqueue(object : Callback<TopMovieResponse> {
                    override fun onResponse(
                        call: Call<TopMovieResponse>,
                        response: Response<TopMovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            topMovieData = response.body()!!.results
                            movieDataaaaaa.plusAssign(topMovieData)
                            appDatabase.topMovieDao().addTopMovies(topMovieData)
                            callBack.onTopMovieReady(movieDataaaaaa)
                        } else if (response.code() in 400..404) {
                            val msg = "Simple Error, please try again"
                            callBack.onMovieLoadingError(msg)
                        }
                    }

                    override fun onFailure(call: Call<TopMovieResponse>, t: Throwable) {
                        val msg = "Error while getting Movie Data"
                        callBack.onMovieLoadingError(msg)
                        callBack.onTopMovieReady(appDatabase.topMovieDao().getAllTopMovies())
                    }
                })
        }
    }



    fun createDatabase(context: Context) {
        appDatabase = AppDatabase.getDatabase(context)
    }


    interface MovieCallback {
        fun onMovieReady(Movie: List<resultsList>)
        fun onTopMovieReady(TopMovie : List<resultsList2>)
        fun onMovieLoadingError(errorMsg: String)
    }


}