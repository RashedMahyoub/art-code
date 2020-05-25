package com.snipertech.cryptobud.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.snipertech.cryptobud.R
import com.snipertech.cryptobud.view.adapter.SectionsPagerAdapter
import com.snipertech.cryptobud.viewModel.HistoryFragmentViewModel


class MainActivity : AppCompatActivity() {

    private var TEXT_1 = "1"
    private var TEXT_2 = "2"
    private lateinit var historyFragmentViewModel: HistoryFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        historyFragmentViewModel = ViewModelProvider(this)
            .get(HistoryFragmentViewModel::class.java)

        val text = intent.getStringExtra(TEXT_1)
        val key = intent.getStringExtra(TEXT_2)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)

        //setup tab layout with view pager
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager,
                text,
                key
            )

        //setup view pager with tab layout
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)


        fab.setOnClickListener { view ->
            val intent = Intent(this, AddMessageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        historyFragmentViewModel.deleteAll()
        Toast.makeText(applicationContext, "All messages deleted", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}