package com.androidcourse.myapplication.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.model.Movie
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Exception
import java.util.*

class AddActivity : AppCompatActivity() {

    private val TAG = "AddActivity"

    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Lets us use JodaTime
//        JodaTimeAndroid.init(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Game"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener { view ->
//            save()
        }
    }

//    private fun save() {
//        val title = etGameTitle.text.toString()
//        val platform = etPlatform.text.toString()
//        val day = etReleaseDay.text.toString()
//        val month = etReleaseMonth.text.toString()
//        val year = etReleaseYear.text.toString()
//
//        val date = ToDate(day, month, year)
//
//        if(title.isNotBlank() && platform.isNotBlank() && day.isNotBlank()
//            && month.isNotBlank() && year.isNotBlank()) {
//            if(date != null) {
//                val game = Movie(title, date, platform)
//                Log.e(TAG, "game=" + game)
//                print(game)
//                addViewModel.insertGame(game)
//                finish()
//            }
//        } else {
//            Toast.makeText(this, "Please fill in the input fields!", Toast.LENGTH_LONG).show()
//        }
//    }

//    private fun ToDate(day: String, month: String, year: String): Date? {
//        //Todo implement correctly
//        var date = DateTime.now()
//        try {
//            date = DateTime(year.toInt(), month.toInt(), day.toInt(),0, 0)
//        } catch (e: Exception) {
//            Toast.makeText(this, "The date is incorrect", Toast.LENGTH_LONG).show()
//        }
//        val fmt = DateTimeFormat.forPattern("dd, MM, yyyy")
//        val formattedDate = fmt.print(date)
//        Log.e(TAG, "date=" + date.toDate())
//        Log.e(TAG, "date2=" + date)
//        Log.e(TAG, "dateformatted=" + formattedDate)
//        return date.toDate()
//
//    }

    private fun initViewModel() {
        addViewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
    }
}
