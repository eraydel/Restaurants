package com.dev.eraydel.restaurants

import com.google.gson.annotations.SerializedName


data class Restaurantes (
  @SerializedName("id"                    ) var id                   : Int?               = null,
  @SerializedName("nombre"                ) var nombre               : String?            = null,
  @SerializedName("calificacion_promedio" ) var calificacionPromedio : Int?               = null,
  @SerializedName("anio_creacion"         ) var anioCreacion         : String?            = null,
  @SerializedName("costo_promedio"        ) var costoPromedio        : Int?               = null,
  @SerializedName("fotos"                 ) var fotos                : ArrayList<String>? = null,
  @SerializedName("resenia"               ) var resenia              : ArrayList<Resenias>? = null,
  @SerializedName("telefono"              ) var telefono             : String?            = null,
  @SerializedName("direccion"             ) var direccion            : String?            = null
)