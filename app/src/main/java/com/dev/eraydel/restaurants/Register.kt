package com.dev.eraydel.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.btnSignUp).setOnClickListener{
            Toast.makeText(this,"Solo se muestran los elementos GUI.", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val intent = Intent(this,Login::class.java)
            this.startActivity(intent)
        }
    }
}