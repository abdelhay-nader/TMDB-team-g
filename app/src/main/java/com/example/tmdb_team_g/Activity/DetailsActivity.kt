package com.example.tmdb_team_g.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb_team_g.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


    // using the bundle to get the data transferred by the intent.
        var bundle: Bundle? = intent.extras

        // making new variables that equal the data transferred
        var overView: String? = bundle?.getString("overView")
        var releaseDate: String? = bundle?.getString("releaseDate")
        var original_language: String? = bundle?.getString("original_language")
        var poster: String? = bundle?.getString("poster_path")
        var myPoster: String? = "https://image.tmdb.org/t/p/w500/${poster}"

    // displaying the data into the UI (activity_details)
        myOverView.text = overView
        myReleaseDate.text = releaseDate
        myOriginal_language.text = original_language

        Picasso.get().load(myPoster).into(myPosterImage)






    }
}