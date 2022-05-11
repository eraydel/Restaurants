package com.dev.eraydel.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev.eraydel.restaurants.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        welcome()

        binding.btnExplore.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            auth.signOut()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }

    private fun welcome(){
        val user = auth.currentUser
        user?.let {
            val email = user.email
            val dataUser = email?.let { it1 -> db.collection("users").document(it1) }
            dataUser?.get()
                ?.addOnSuccessListener {
                    if( it != null){
                        binding.textHome.text = "Saludos, "
                        binding.tvUsername.text = "${it.data?.get("name")}"
                        binding.tvEmail.text = "${it.data?.get("email")}"
                    }
                }?.addOnFailureListener { exception ->
                    Log.d("firestore", "get failed with ", exception)
                }

        }
    }
}