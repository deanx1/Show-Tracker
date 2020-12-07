package com.androidcourse.myapplication.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

class AddActivity : AppCompatActivity() {

    private val TAG = "AddActivity"

    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.add_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener { view ->
            getSearch()
        }
    }

    // Make the back button in the toolbar return to the previous activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getSearch() {
        val searchTitle = etTitle.text.toString()

        if(searchTitle.isNotBlank()) {
//            val search = Movie(searchTitle,)
            Log.e(TAG, "search: " + searchTitle)
            addViewModel.getSearch(searchTitle)
            finish()

        } else {
            Toast.makeText(this, "Please fill in the input fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun initViewModel() {
        addViewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
    }
}
