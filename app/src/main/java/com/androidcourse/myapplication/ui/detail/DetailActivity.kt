package com.androidcourse.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.ui.main.MOVIE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

//    private val movieRepository = MovieRepository(application.applicationContext)

    private lateinit var detailViewModel: DetailActivityViewModel
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Show an empty title
        supportActionBar?.title = ""
        initViews()
        initViewModel()
    }

    private fun initViews() {

        val movie: Movie = intent.extras?.getParcelable(MOVIE)!!

        tvOverviewContent.text = movie.overview
        tvRating.text = movie.average_rating.toString()
        tvReleaseDate.text = movie.release_date
        tvTitle.text = movie.title
        tvGenre.text = movie.genre1
        tvGenre2.text = movie.genre2
        tvGenre3.text = movie.genre3
        Glide.with(this).load(movie.getBackdrop()).into(ivBackdrop)
        Glide.with(this).load(movie.getPoster()).into(ivMoviePoster)

        btnRemove.setOnClickListener {
            ioScope.launch {
                detailViewModel.deleteMovie(movie)
                finish()
            }
        }
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

    private fun initViewModel() {
        detailViewModel = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
    }
}
