package com.androidcourse.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MenuItem
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.ui.main.MOVIE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Show an empty title
        supportActionBar?.title = ""
        initViews()
    }

    private fun initViews() {
        val movie: Movie = intent.extras?.getParcelable(MOVIE)!!

        // No longer needed as textView is inside ScrollView
//        tvOverviewContent.setMovementMethod(ScrollingMovementMethod())
//        tvOverviewContent.movementMethod = ScrollingMovementMethod()
        tvOverviewContent.text = movie.overview
        tvRating.text = movie.average_rating.toString()
        tvReleaseDate.text = movie.release_date
        tvTitle.text = movie.title
        Glide.with(this).load(movie.getBackdrop()).into(ivBackdrop)
        Glide.with(this).load(movie.getPoster()).into(ivMoviePoster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
