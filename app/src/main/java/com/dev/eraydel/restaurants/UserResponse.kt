package com.dev.eraydel.restaurants

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("usuarios" ) var users: List<Users>
)
