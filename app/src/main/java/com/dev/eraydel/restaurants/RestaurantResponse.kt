package com.dev.eraydel.restaurants

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("restaurantes" ) var restaurantes : ArrayList<Restaurantes> = arrayListOf()
)
