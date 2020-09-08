package com.tcs.newsapp.LoginPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_sucess.*

class LoginSucess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sucess)
        val extras = intent.extras
       val username = extras?.get("Username")
        welcomeText.text="WELCOME :- $username"
        logout_button.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity( Intent(this,Dashboard::class.java))
            finish()
        }
    }
}