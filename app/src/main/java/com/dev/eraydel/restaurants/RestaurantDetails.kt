package com.dev.eraydel.restaurants

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class RestaurantDetails : AppCompatActivity() {
    private lateinit var  name: String
    private lateinit var photo: String
    private lateinit var photo2: String
    private lateinit var photo3: String
    private lateinit var photo4: String
    private lateinit var review_title: String
    private lateinit var review_description: String
    private lateinit var review_user: String
    private lateinit var review_date: String
    private lateinit var review_rate: String
    private lateinit var review_address: String
    private lateinit var review_telephone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        if(savedInstanceState == null)
        {
            val bundle = intent.extras

            name = if( bundle != null ){
                bundle.getString("nombre" , "name")
            } else {
                savedInstanceState?.getSerializable("nombre") as String
            }

            photo = if( bundle != null ){
                bundle.getString("foto" , "foto")
            } else {
                savedInstanceState?.getSerializable("foto") as String
            }

            review_title = if( bundle != null ){
                bundle.getString("review_title" , "review_title")
            } else {
                savedInstanceState?.getSerializable("review_title") as String
            }

            review_description = if( bundle != null ){
                bundle.getString("review_description" , "review_description")
            } else {
                savedInstanceState?.getSerializable("review_description") as String
            }

            review_date = if( bundle != null ){
                bundle.getString("review_date" , "review_date")
            } else {
                savedInstanceState?.getSerializable("review_date") as String
            }

            review_rate = if( bundle != null ){
                bundle.getString("review_rate" , "review_rate")
            } else {
                savedInstanceState?.getSerializable("review_rate") as String
            }

            review_address = if( bundle != null ){
                bundle.getString("review_address" , "review_address")
            } else {
                savedInstanceState?.getSerializable("review_address") as String
            }

            review_telephone = if( bundle != null ){
                bundle.getString("review_telephone" , "review_telephone")
            } else {
                savedInstanceState?.getSerializable("review_telephone") as String
            }

            photo2 = if( bundle != null ){
                bundle.getString("photo_second" , "photo_second")
            } else {
                savedInstanceState?.getSerializable("photo_second") as String
            }

            photo3 = if( bundle != null ){
                bundle.getString("photo_third" , "photo_third")
            } else {
                savedInstanceState?.getSerializable("photo_third") as String
            }

            photo4 = if( bundle != null ){
                bundle.getString("photo_fourth" , "photo_fourth")
            } else {
                savedInstanceState?.getSerializable("photo_fourth") as String
            }
        }

        supportActionBar?.title = "Restaurant Detail"
        var ivItem = findViewById<ImageView>(R.id.ivPicture)
        Picasso.get().load(photo).into(ivItem)
        findViewById<TextView>(R.id.tvNombre).text = name.toString()
        findViewById<TextView>(R.id.tvReviewTitle).text = review_title.toString()
        findViewById<TextView>(R.id.tvReviewDescripcion).text = review_description.toString()
        findViewById<TextView>(R.id.tvReviewDate).text = review_date.toString()
        //findViewById<TextView>(R.id.tvUser).text = review_user.toString()

        findViewById<RatingBar>(R.id.rating).numStars = 5
        findViewById<RatingBar>(R.id.rating).stepSize = 0.5F
        findViewById<RatingBar>(R.id.rating).rating = review_rate?.toFloat()?.div(2) ?: 0.0F

        findViewById<TextView>(R.id.tvDireccion).text = review_address.toString()
        findViewById<TextView>(R.id.tvTelefono).text = review_telephone.toString()

        var ivSecond = findViewById<ImageView>(R.id.ivItem1)
        Picasso.get().load(photo2).into(ivSecond)

        var ivThird = findViewById<ImageView>(R.id.ivItem3)
        Picasso.get().load(photo3).into(ivThird)

        var ivFourth = findViewById<ImageView>(R.id.ivItem4)
        Picasso.get().load(photo4).into(ivFourth)
    }
}