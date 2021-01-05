package com.androidcourse.myapplication.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.androidcourse.myapplication.R
import com.androidcourse.myapplication.ui.add.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private val TAG = "SettingsActivity"

    private lateinit var addViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

//        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.settings_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
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

    private fun initViewModel() {
        addViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }
}
