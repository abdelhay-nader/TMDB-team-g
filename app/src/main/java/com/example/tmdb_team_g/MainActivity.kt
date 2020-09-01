package com.example.tmdb_team_g

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiServices : ApiServices = RetrofitClient.getClient().create(ApiServices::class.java)
        var call : retrofit2.Call<MovieResponse> = apiServices.getMovieByApi("099a5418f2bec70523fe74e21f45b456")


        call.enqueue(object : Callback<MovieResponse> {
            @SuppressLint("WrongConstant")
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>) {

                val movieResourseList : List<resultsList> = response.body()!!.results
                recyclerview_tmdb.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL,false)
                recyclerview_tmdb.adapter=MoviesAdapter(movieResourseList)


            }

            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                println(t.message)
                call.cancel()
            }
        } )




    }
}