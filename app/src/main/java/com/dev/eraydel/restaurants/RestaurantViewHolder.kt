package com.dev.eraydel.restaurants

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantViewHolder(view: View , onItemListener: RestaurantAdapter.OnItemListener) : RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var ivItem: ImageView = view.findViewById(R.id.ivItem)
    private var tvItemTitle: TextView = view.findViewById(R.id.tvItemTitle)
    private var rate: RatingBar = view.findViewById(R.id.ratingBar)
    private var tvItemDesde: TextView = view.findViewById(R.id.tvDesde)
    private var tvItemCostoPromedio: TextView = view.findViewById(R.id.tvCostoPromedio)

    private val onItemListener = onItemListener
    private lateinit var restaurant: Restaurantes

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View){
        onItemListener.clickRestaurant(restaurant)
    }

    fun bind(item: Restaurantes)
    {
        tvItemTitle.text = item.nombre?.toString()
        Picasso.get().load(item.fotos?.first()).into(ivItem)
        rate.numStars = 5
        rate.stepSize = 0.5F
        rate.rating = item.calificacionPromedio?.toFloat()?.div(2) ?: 0.0F
        tvItemDesde.text = item.anioCreacion.toString()
        tvItemCostoPromedio.text = "$"+item.costoPromedio.toString()
        restaurant = item

    }





}