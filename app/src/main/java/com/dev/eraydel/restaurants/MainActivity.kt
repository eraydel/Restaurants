package com.dev.eraydel.restaurants

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RestaurantAdapter.OnItemListener{

    private lateinit var adapter : RestaurantAdapter
    private val restaurantsImages = ArrayList<Restaurantes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllRestaurants()
        initRecyclerView()
    }


/*
    Method: initRecyclerView
    Created by: Erick Ayala
    Created at: 23/04/2022
     */
    private fun initRecyclerView(){
        adapter = RestaurantAdapter(restaurantsImages, this)
        findViewById<RecyclerView>(R.id.rvRestaurants).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvRestaurants).addItemDecoration(DividerItemDecoration(applicationContext,1))
        findViewById<RecyclerView>(R.id.rvRestaurants).adapter = adapter

    }


    /*
    Method: getRetrofit
    Created by: Erick Ayala
    Created at: 23/04/2022
     */
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://demo1416549.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*
    Method: initRecyclerView
    Created by: Erick Ayala
    Created at: 23/04/2022
     */

    private fun getAllRestaurants(){
        Toast.makeText(this,"¡Nuestras mejores recomendaciones!",Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
          val call: Response<RestaurantResponse> = getRetrofit().create(APIService::class.java).getAllRestaurants("listarestaurantes")
          val allRestaurants:RestaurantResponse? = call.body()

          runOnUiThread {
              if(call.isSuccessful){
                  var restaurants: ArrayList<Restaurantes> = (allRestaurants?.restaurantes ?: emptyArray<RestaurantResponse>()) as ArrayList<Restaurantes>
                  restaurantsImages.clear()
                  restaurantsImages.addAll(restaurants)
                  adapter.notifyDataSetChanged()
              }
          }
        }
    }

    override fun clickRestaurant(item: Restaurantes) {
        Toast.makeText(this, "Movie: ${item.nombre}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,RestaurantDetails::class.java)
        intent.putExtra("nombre" , item.nombre)
        intent.putExtra("foto" , item.fotos?.first())
        intent.putExtra("review_title" , item.resenia?.first()?.titulo_resenia.toString())
        intent.putExtra("review_description" , item.resenia?.first()?.resenia_descripcion.toString())
        intent.putExtra("review_date" , item.resenia?.first()?.fecha_resenia.toString())
        intent.putExtra("review_rate" , item.resenia?.first()?.calificacion_usuario.toString())
        intent.putExtra("review_address" , item.direccion)
        intent.putExtra("review_telephone" , item.telefono)
        intent.putExtra("photo_second" , item.fotos?.get(2)?.toString())
        intent.putExtra("photo_third" , item.fotos?.get(3)?.toString())
        intent.putExtra("photo_fourth" , item.fotos?.get(1)?.toString())
        startActivity(intent)
    }
}




