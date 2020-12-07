package com.androidcourse.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
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
import kotlinx.coroutines.*

const val MOVIE = "MOVIE"
const val TAG = "MAIN"

class MainActivity : AppCompatActivity() {

    val movies = arrayListOf<Movie>()
    val movieAdapter =
        MovieAdapter(movies) { movie ->
            startDetailActivity(movie)
            Log.e("MAIN", "testtest: " + movie)
        }

    lateinit var viewModel: MainActivityViewModel

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

        rvMovies.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.movies.observe(this, Observer {
            this@MainActivity.movies.clear()

            // sort list by title
            val sortedList = movies.sortedWith(compareBy({it.title}))
            this@MainActivity.movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun startDetailActivity(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
