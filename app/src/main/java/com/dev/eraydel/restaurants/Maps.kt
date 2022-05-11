package com.dev.eraydel.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.dev.eraydel.restaurants.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding
    private lateinit var id: String
    // maps es el objeto que representa la clase de GoogleMap
    private lateinit var map:GoogleMap
    // para ubicar al usuario
    companion object {
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Google Maps Location"

        if(savedInstanceState == null) {
            val bundle = intent.extras

            id = if (bundle != null) {
                bundle.getString("id", "0")
            } else {
                savedInstanceState?.getSerializable("id") as String
            }
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        createFragment()
    }



    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //este codigo se ejecutara cuando el fragment termine de cargarse
        map = googleMap
        when(id.toInt()) {
            1 -> {
                binding.tvTitle.text = "La Distral"
                crearMrker(19.432544046140645, -99.1546606587552,"La Distral")
            }
            2 -> {
                binding.tvTitle.text = "Parrilla Urbana Division del Norte"
                crearMrker(19.385813039459308, -99.16120501642679,"Parrilla Urbana Division del Norte")
            }
            3 -> {
                binding.tvTitle.text = "Sonora Grill Coapa"
                crearMrker(19.295158689391776, -99.12865227409894,"Sonora Grill Coapa")
            }
            4 -> {
                binding.tvTitle.text = "Condimento Emporio Reforma"
                crearMrker(19.43166624851222, -99.15684314887213,"Condimento Emporio Reforma")
            }
            5 -> {
                binding.tvTitle.text = "Condimento Restaurant"
                crearMrker(19.427857771932402, -99.16398342722691,"Condimento Restaurant")
            }
        }


    }

    private fun crearMrker(lat: Double, lon: Double, txtUbicacion: String){
        val coordenadas = LatLng(lat,lon )
        val marker = MarkerOptions().position(coordenadas).title(txtUbicacion)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenadas,18f),
            4000,null
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.home) {
            val intent = Intent(this,Home::class.java)
            intent.putExtra("id" , id)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}