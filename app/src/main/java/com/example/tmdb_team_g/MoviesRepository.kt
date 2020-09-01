package com.example.tmdb_team_g




import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MoviesRepository {

    private val apiServices : ApiServices by lazy{
        RetrofitClient.getClient().create(ApiServices::class.java)
    }


    private lateinit var movieData : List<resultsList>

    fun requestMovieData (callBack : MovieCallback ) {

        if (this :: movieData.isInitialized){
                callBack.onMovieReady(movieData)
                return
        }




        apiServices.getMovieByApi("099a5418f2bec70523fe74e21f45b456").
        enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                println("OnResponseCalled")
                if (response.isSuccessful){
                    movieData = response.body()!!.results
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
            }
        })


    }












    interface MovieCallback {
        fun onMovieReady(Movie: List<resultsList>)
        fun onMovieLoadingError(errorMsg: String)
    }


















}