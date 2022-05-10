package com.dev.eraydel.restaurants

import com.google.gson.annotations.SerializedName

data class Users(
    var id: Int,
    @SerializedName("username" )    var nombre_usuario: String,
    @SerializedName("firstname" )   var nombre: String,
    @SerializedName("middlename" )  var apellido_paterno: String,
    @SerializedName("lastname" )    var apellido_materno: String,
    @SerializedName("password" )    var password: String,
    @SerializedName("email" )       var email: String,
    @SerializedName("phone" )       var telefono: String,
    @SerializedName("status" )      var activo: String,
)
