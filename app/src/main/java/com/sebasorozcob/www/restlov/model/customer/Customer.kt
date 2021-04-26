package com.sebasorozcob.www.restlov.model.customer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Customer(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("address")
    val address: @RawValue Location,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("followRestaurants")
    val followRestaurants: @RawValue List<RestaurantItem>?,
) : Parcelable