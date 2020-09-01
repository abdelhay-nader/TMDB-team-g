package com.example.tmdb_team_g

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiServices : ApiServices = RetrofitClient.getClient().create(ApiServices::class.java)
        var call : Call<MovieResponse> = apiServices.getMovieByApi("099a5418f2bec70523fe74e21f45b456")


        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                val movieResourseList : List<resultsList> = response.body()!!.results

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println(t.message)
                call.cancel()
            }
        } )




    }
}