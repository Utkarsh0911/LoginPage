package com.tcs.newsapp.LoginPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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

//        login_button.setOnClickListener{
//            FirebaseAuth.getInstance().signOut()
//            startActivity( Intent(this,Dashboard::class.java))
//            finish()
//        }

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

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    }
}