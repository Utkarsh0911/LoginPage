package com.tcs.newsapp.LoginPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tcs.newsapp.LoginPage.ui.dashboard.DashboardFragment
import com.tcs.newsapp.LoginPage.ui.home.HomeFragment
import com.tcs.newsapp.LoginPage.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_login_sucess.*


class LoginSucess : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sucess)
        val extras = intent.extras
       val username = extras?.get("Username")

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.login_success_toolbar)
        setSupportActionBar(toolbar)



makeCurrentFragment(HomeFragment())
        bottomLayout.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home-> makeCurrentFragment(HomeFragment())
R.id.navigation_dashboard -> makeCurrentFragment(DashboardFragment())
                R.id.navigation_notifications -> makeCurrentFragment(NotificationsFragment())
            }
            true

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper,fragment)
                .commit()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         when (item.itemId) {
            R.id.log_out -> {


                val intent = Intent(this, Dashboard::class.java)
                this.startActivity(intent)
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
return false

    }}