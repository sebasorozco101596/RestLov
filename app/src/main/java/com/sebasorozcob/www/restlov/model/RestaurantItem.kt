package com.sebasorozcob.www.restlov.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.purchases.ChallengeProgress
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RestaurantItem(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("followers")
    val followers: @RawValue List<Any>?,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("location")
    val location: @RawValue Location,
    @SerializedName("menu")
    val menu: @RawValue List<Menu>?,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("priceLevel")
    val priceLevel: Int,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("challenges")
    val challenges: @RawValue List<Challenge>?
) : Parcelable