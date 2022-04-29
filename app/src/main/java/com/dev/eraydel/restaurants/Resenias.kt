package com.dev.eraydel.restaurants

import com.google.gson.annotations.SerializedName

data class Resenias(
    @SerializedName("nombre_usuario"               ) var nombre_usuario         : String? = null,
    @SerializedName("titulo_resenia"               ) var titulo_resenia         : String? = null,
    @SerializedName("resenia_descripcion"          ) var resenia_descripcion    : String? = null,
    @SerializedName("fecha_resenia"                ) var fecha_resenia          : String? = null,
    @SerializedName("calificacion_usuario"         ) var calificacion_usuario   : String? = null
)
