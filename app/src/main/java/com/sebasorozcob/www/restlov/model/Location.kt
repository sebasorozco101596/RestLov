package com.sebasorozcob.www.restlov.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("zipcode")
    val zipcode: String
) : Parcelable