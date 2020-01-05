package com.androidcourse.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.ui.add.AddActivity
import com.androidcourse.myapplication.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val MOVIE = "MOVIE"

class MainActivity : AppCompatActivity() {


    private val colors = arrayListOf<Movie>()
    private val movieAdapter =
        MovieAdapter(colors) { movie ->
            startDetailActivity(movie)
        }
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        submitButton.setOnClickListener {
            val year = tiMovieYearInput.text.toString()
            viewModel.getMovies(year)
        }
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            colors.clear()
            colors.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun startDetailActivity(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }

}
